(ns clojure-learn.simpleio)

(defn read-n
  "read n elements from stdin into a vector of strings"
  [n]
  (loop [
         x n
         acc []
         ]
    (if (= x 0)
      acc
      (recur (dec x) (conj acc (read-line))))))

(defn write-n
  [out]
  (doseq [item out] (println item)))
