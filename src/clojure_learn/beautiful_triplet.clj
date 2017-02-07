(ns clojure-learn.beautiful-triplet
  (use [clojure.string :only (split triml)])
  (:gen-class))


; Beautiful Triplet
;
; https://www.hackerrank.com/challenges/beautiful-triplets
;

(defn create-hashmap
  [data d]

  (defn update-hashmap
    "add item to hashmap"
    [h item]
    (let [
          r (rem item d)
          v (or (h r) [])  ; is there a an entry for this remainder
          ]
      (assoc h r (conj v item))))

  (reduce update-hashmap {} data))

(defn beautiful?
  "given a triplet in a vector form, determine if this is a beautiful triplet or not"
  [d triplet]
  (let [
      d1 (- (triplet 1) (triplet 0))
      d2 (- (triplet 2) (triplet 1))
        ]
    (and (= d1 d2) (= d1 d))))

(defn triplets
  "generate all the triplets from data"
  ([data] (triplets [] data))
  ([acc data]
   (if (< (count data) 3)
     acc
     (recur (conj acc (into [] (take 3 data))) (rest data))
     )))

(defn -main
  "Main loop"
  [& args]
  (let [
        [n d ] (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        data  (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        h     (create-hashmap data d)
        ]
    (println h)))
