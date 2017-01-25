(ns clojure-learn.sock
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Sock Merchant
; 
; Solution for https://www.hackerrank.com/challenges/sock-merchant
;

; Assume a hashmap that tracks the number of each type of sock. For
; exampe {10:2 20:7} means that there are 2 socks of type "10", etc

(defn update-counts
  [h s]
  (let [
      count (h s)
      ]
      (if (nil? count)
        (conj h {s 1})
        (conj h {s (inc count)}))))

; returns a hashmap that contains the count of each type of sock
(defn create-map
    [socks]

    (defn inner
      [h socks]
      (if (empty? socks)
        h
        (recur (update-counts h (first socks)) (rest socks))))

    (inner {} socks))

(defn count-pairs
    [h]
    (reduce + (map #(quot % 2) (vals h))))

(defn pairs
    [socks]
    (count-pairs (create-map socks)))

(defn -main
    "Main loop"
      [& args]
    (let [
          n (read-line) ; number of socks
          socks (into [] (map #(Integer/parseInt %) (split (read-line) #"\s+")))
          p (pairs socks)     
        ]
        (println p)))