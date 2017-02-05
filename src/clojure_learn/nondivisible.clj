(ns clojure-learn.nondivisible
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Try this with an example
; k = 6.
; must be divisible by 12
; [6 1 7 2 4 9 3]   count = 6
;
; For any number K, the sum of 2 values (A & B) is evenly divisible by K
; if the sum of the remainders of A/K + B/K is K or 0
;
; for k = 6, the sum of the remainders must be 6. The pairs are (0 0) (1 5) (2 4) (3 3)
;
; rem 0 {6}
; rem 1 {1 7}
; rem 2 {2}
; rem 3 {9 3}
; rem 4 {4}
; rem 5 {}
;
; (0 0)  {6}
; (1 5)  {1 7} {4} => larger set is {1 7}
; (2 4)  {2} {4} => larger set is {2}/{4}
; {3 3}  {9 3}     => we can choose only 1. so {3}/{9}

; the maximal subset is {6} {1 7} {2}/{4} {3}/{9}
; the count is 1 + 2 + 1 + 1 = 5


(defn create-pairs
  "returns a set of pairs such that the elements of the pair sum up to k. Each pair is modelled as a list of two elements"
  [k]
  (let [
        tmp (range 1 (inc (quot k 2)))
        pairs-list (map #(list %1 (- k %1)) tmp)
        ]
    (reduce conj #{} pairs-list)
    ))


(defn maximal-subset
  "return the size of the maximal subset such that the sum of no two elements is divisible by 2k"
  [data k]
  0)

(defn -main
  "Main loop"
  [& args]
  (let [
        [n k] (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        data  (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        ]
    (println n k)
    (println data)))

