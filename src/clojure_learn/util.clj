(ns clojure-learn.util
  (use [clojure.string :only (split triml)])
  (use [clojure.set :only (union)])
  (:gen-class))

(defn subsets
  "Set of all the subsets"
  [s]
  (cond
    (= 0 (count s))   #{ #{} }
    (= 1 (count s))   #{ #{} #{(first s)} }
    :else (let [
                f1  (first s)
                r1  (into #{} (rest s))
                sf1 #{ #{f1} }
                sr1 (subsets r1)
                sr2 (map #(conj % f1) sr1)
                ]
            (into #{} (clojure.set/union #{} sr1 sr2)))
    ))

(defn pairs
  "set of pairs from a set"
  [data]
  (if (= 2 (count data))
    #{data}  ; returning a set of sets ...
    (let [
          f1  (first data)  ; first element
          r1  (into #{} (rest data))
          s1  (into #{} (map #(hash-set f1 %) r1))
          s2  (pairs r1)
          ]
      (clojure.set/union s1 s2))))

(defn log2
  "log to the base 2"
  [x]
  (/ (Math/log x) (Math/log 2)))

(defn exp [x n]
  "does x ^ n"
  (reduce * (repeat n x)))

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