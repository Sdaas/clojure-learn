(ns clojure-learn.beautifulday
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; Beautiful Day at the Movies
; 
; https://www.hackerrank.com/challenges/beautiful-days-at-the-movies
;

(defn reverse-integer
    "reverse an integer"
    ([n] (reverse-integer 0 n))
    ([acc n]
        (if (= 0 n)
            acc
            (recur (+ (* 10 acc) (rem n 10)) (quot n 10)))))

(defn beautifulday?
    "returns true if this a beautiful day false otherwise"
    [n k]
    (let [
        num (Math/abs (- n (reverse-integer n)))
        ]
        (= 0 (rem num k))))

(defn beautifuldays 
    [i j k]
    (let [
        days      (range i (inc j)) ; days must include both i and j
        ]
        (count (filter #(beautifulday? % k) days))))

(defn -main
  "Main program"
	[& args]
 	(let [
        [i j k]   (map #(Integer/parseInt %) (split (read-line) #"\s+"))      
        ]
        (println (beautifuldays i j k))))

