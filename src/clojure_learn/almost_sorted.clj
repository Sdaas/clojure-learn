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

  ; x1 .. xk y1 .... yk z1..zk
  (let [
        {start :start end :end} (descending data)     ; ASSUMES that retval is not nil. i.e., list is not already sorted
        sequence-at-start? (= start 0)                ; if the descending sequence is at the start (xk does not exist)
        sequence-at-end?   (= (inc end) (count data)) ; if the descending sequence is at the end (z1 does not exist)
        c1  (or sequence-at-end? (< (data start) (data (inc end)))) ; y1 < z1, or z1 does not exist
        c2  (or sequence-at-start? (> (data end) (data (dec start)))) ; yk > xk. or xk does not exist
        op (-operation (- end start))
        fixable? (and c1 c2) ; yk > xk and y1 < z1
        ]
    (if fixable? {:op op :start (inc start) :end (inc end) } {:op "no" }))) ; convert start/end from 0 offset to 1 offset

(defn -main
  "Main loop"
  [& args]
  (let [
        n (Integer/parseInt (read-line))
        data  (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        {op :op start :start end :end} (fix data)
        ]
      (if (= op "no")
        (println "no")
        (do
          (println "yes")
          (println op start end)
          ))))
