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