(ns clojure-learn.almost-sorted
  (use [clojure.string :only (split triml)])
  (:gen-class))


; Almost Sorted
;
; https://www.hackerrank.com/challenges/almost-sorted
;

; if second > first => subsequence(list)

(defn foo
  "find the index of the descending subsequence"
  ([data] (foo data 0))
  ([data index]

   (defn subseq
     [data pred msg index]
     (println msg data index)
     (if (< (count data) 2)
       {:index index :data data}
       (let [
             [f s] data ;; extracting the first, second, and the rest respectively
             ]
         (if (pred s f)
           (recur (rest data) pred msg (inc index))
           {:index index :data data}))))

   (let [
         {idx1 :index data1 :data} (subseq data > "AAA" index) ; idx1 is where ascending sequence stops
         {idx2 :index data2 :data} (subseq data1 < "DDD" index) ; index IN DATA1 where the descending seq stops
         idx3 (+ idx1 idx2) ; remember that idx1 and idx2 are zero offset based
         ]
     (println " idx1=" idx1)
     (println "data1=" data1)
     (println " idx3=" idx3)
     )))



(defn -main
  "Main loop"
  [& args]
  (let [
        n (Integer/parseInt (read-line))
        data  (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        ]
    (println "hello")))
