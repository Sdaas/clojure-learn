(ns clojure-learn.almost-sorted
  (use [clojure.string :only (split triml)])
  (:gen-class))


; Almost Sorted
;
; https://www.hackerrank.com/challenges/almost-sorted
;

; In a sequence x1 ... xk y1 .... yk z1 ... zk
; where x1 .. xk is monotonically increasing
; and   z1 .. zk is monotonicall increasing
; and   y1 .. yk is monotonically decreasing
;
; it is possible convert the sequence by reversing y1..yk only if
; * y1 .. yk is monotonically decreasing
; and yk > xk
; and y1 < z1
;
; [10 20 40 30 50 ] => descending sequence is [40 30]. 40 < 50 and 30 > 20
; [30 10 20]        => descending sequence is [30 10]. 30 < 20 FALSE. so it is not possible to conver the sequence

(defn descending
  "find the index (offset 0 based) of the descending subsequence. Returns nil if no descending sequence is gound"
  ([data] (descending data 0))
  ([data index]

   (defn -subseq
     [data pred msg index]
     ;(println msg data index)
     (if (< (count data) 2)
       {:index index :data data}
       (let [
             [f s] data ;; extracting the first, second, and the rest respectively
             ]
         (if (pred s f)
           (recur (rest data) pred msg (inc index))
           {:index index :data data}))))

   (if (empty? data)
     nil
     (let [
           {idx1 :index data1 :data} (-subseq data > "AAA" index) ; idx1 is where ascending sequence stops
           {idx2 :index data2 :data} (-subseq data1 < "DDD" index) ; index IN DATA1 where the descending seq stops
           idx3 (+ idx1 idx2) ; remember that idx1 and idx2 are zero offset based
           ]
       (if (= (inc idx1) (count data))
         nil  ; This is a purely ascending sequence. So no match
         {:start idx1 :end idx3})))))

(defn fix
  [data]

  (defn -operation
    [len]
    (cond
      (= 0 len) "no"
      (= 1 len) "swap"
      :else "reverse"))

  (let [
        {start :start end :end} (or (descending data) {:start 0 :end 0})
        ;c1     ; yk > xk
        ;fixable? (and (> (data end) (data (dec start))) (< (data start) (data (inc end))))  ; yk > xk and y1 < z1
        op (-operation (- end start))
        ]
    {:op op :start start :end end}))

(defn -main
  "Main loop"
  [& args]
  (let [
        n (Integer/parseInt (read-line))
        data  (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        ]
    (println "hello")))
