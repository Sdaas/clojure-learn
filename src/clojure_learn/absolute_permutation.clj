(ns clojure-learn.absolute-permutation
  (use [clojure-learn.simpleio :only (read-n write-n)])
  (use [clojure.string :only (split triml)])
  (:gen-class))


; Absolute Permutation
;
; https://www.hackerrank.com/challenges/absolute-permutation
;

; Given a list of items in lexicographic order, generate all the permutations also in lexicographic order
; (p 1 2 3 4) = 1 (p 2 3 4), 2 (p 1 3 4), 3 (p 1 2 4), 4 (p 1 2 3)


; Partition a list around a index. For example (partition 1 [10 20 30 40 50]) will partition
; around index position 1 and return the pivot point 20, and two lists (10) and (30 40 50)
(defn partition3
  "Partition the list into three sections"
  [index data]
  (let [
        [part1 temp] (split-at index data)
        part2        (rest temp)
        pivot        (first temp)
        ]
    {:part1 part1 :pivot pivot :part2 part2}))

(defn valid-pivot?
  "returns true if the data for the (zero index) pos is equal to k"
  [data pos k]
  (= (Math/abs (- data (inc pos))) k))

; Given a list, generate all its permutations
(defn permutations
  ([data]
   (if (= 1 (count data))
     (list data) ; list of permutations ..
     (reduce #(concat %1 (permutations %2 data)) '() (range (count data))))
    )
  ([index data]
   (let [
         {p1 :part1 pivot :pivot p2 :part2} (partition3 index data)
         new-data  (concat p1 p2)  ; data without the pivot
         perms     (permutations new-data) ; all the permutations for new-data
         ]
     (map #(cons pivot %) perms))
    ))

; Given a list, generate all the valid absolute permutations
(defn absolute-permutations
  ([data k depth]
   (if (= 1 (count data))
     (list data) ; list of permutations ..
     (reduce #(concat %1 (absolute-permutations %2 data k depth)) '() (range (count data)))
     )
    )
  ([index data k depth]
   (let [
         {p1 :part1 pivot :pivot p2 :part2} (partition3 index data)
         ]
     (if (valid-pivot? pivot depth k)
       (map #(cons pivot %) (absolute-permutations (concat p1 p2) k (inc depth)))
       (list ) ; there are no valid permutations
       ))))


(defn parse-item
  "Converts a string into an list of integers"
  [item]
  (map #(Integer/parseInt %) (split item #"\s+")))

(defn process
  "return the first absolute-permutation for this n-k pair, nil if there is none"
   [nk-pair]
  (let [
        [n k] nk-pair
        data  (rest (range (inc n)))
        ]
    (println "process " data k)
    (first (absolute-permutations data k 0))))

(defn -main
  "Main loop"
  [& args]
  (let [
        t      (Integer/parseInt (read-line))   ; number of test cases
        strvec (read-n t) ; read all the lines into a vector of strings
        data   (map #(parse-item %) strvec)
        out    (map #(process %) data)
        ]
    (println out)))

