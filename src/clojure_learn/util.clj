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