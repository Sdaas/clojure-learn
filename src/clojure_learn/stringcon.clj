(ns clojure-learn.stringcon
  (use [clojure.string :only (split triml)])
  (use [clojure-learn.simpleio :only (read-n write-n)])
  (:gen-class))

; String construction
;
; https://www.hackerrank.com/challenges/string-construction
;
; Looks like we just need to count the number of distinct chars in the string
;


; returns a hashmap that counts the number of occurences for each letter
(defn create-map
  [s]

  (defn update-map
    "update the current map based on one entry"
    [h k]
    (let [
          v (or (h k) 0)                                      ; the value of the key, or 0 if key not found
          ]
      (assoc h k (inc v))))

  (reduce update-map {} (seq s)))



(defn -main
  "Main loop"
  [& args]
  (let [
        n     (Integer/parseInt (read-line))
        data  (read-n n)
        counts (map #(count (keys (create-map %))) data)
        ]
    (write-n counts)))