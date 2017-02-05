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
; (0 0)  {6}       => we can choose only 1. So {6}
; (1 5)  {1 7} {4} => larger set is {1 7}
; (2 4)  {2} {4} => larger set is {2}/{4}
; {3 3}  {9 3}     => we can choose only 1. so {3}/{9}

; the maximal subset is {6} {1 7} {2}/{4} {3}/{9}
; the count is 1 + 2 + 1 + 1 = 5

; Another example
; k = 5
; evenly divisible by k means divisible by 2k = 10
; data = [5 10 15 20]
; Possible pairs are (0 0) (1 4) (2 3)
; (0 0) => {5 10 15 20} => choose 1 odd and one even multiple of k => {5 10} ( multples are 1 and 2)
; (1 4) => no sets with these remainder
; (2 3} => no sets with these remainder
;
; So the maximal subset is {5 10} / {10 15} etc.
; size of maximal subset is 2

(defn create-pairs
  "returns a set of pairs such that the elements of the pair sum up to k. Each pair is modelled as a list of two elements"
  [k]
  (let [
        tmp (range 1 (inc (quot k 2)))
        pairs-list (map #(list %1 (- k %1)) tmp)
        ]
    (reduce conj #{} pairs-list)
    ))


(defn remainders
  "returns a hashmap where the key n contains a set of all numbers that have remainder n wrt k"
  [data k]

  (defn update-hashmap
    [h coll]
    (if (empty? coll)
      h
      (let [
            f (first coll)
            r (rem f k)      ; the value of the remainder for this item
            v (or (h r) #{}) ; the existign set of numbers in the map for this remainder
            nv (conj v f)    ; add the item to the existing set for this number
            nh (assoc h r nv) ; update the hashmap
            ]
        (recur nh (rest coll)))))

  (update-hashmap {} data))

(defn double-zero
  "Given a hashmap of remainders, the maximal subset for zero-remainders"
  [h k]
  (let [
        nums (h 0)  ; all the numbers that have zero-remainder
        odd-multiples   (count (filter #(odd? (quot % k)) nums))
        even-multiples  (count (filter #(even? (quot % 5)) nums))
        ]
        (+  (if (> odd-multiples 0) 1 0) (if (> even-multiples 0) 1 0))))


(defn max-set-size-for-pair
  [rems pair]
  (let [
        f (first pair)
        s (second pair)
        c1 (count (or (rems f) #{}))
        c2 (count (or (rems s) #{}))
        ]
    (if (= f s)
      (if (= 0 c1) 0 1)
      (max c1 c2))))

(defn size-of-maximal-subset
  "return the size of the maximal subset such that the sum of no two elements is divisible by 2k"
  [data k]
  (let [
        pairs (create-pairs k)
        rems  (remainders data k)
        zzz   (double-zero rems k)
        n     (reduce + (map #(max-set-size-for-pair rems %) pairs))
        ]
    (+ zzz n)))


(defn -main
  "Main loop"
  [& args]
  (let [
        [n k] (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        data  (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        ]
    (println (size-of-maximal-subset data k))))

