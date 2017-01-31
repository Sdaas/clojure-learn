(ns clojure-learn.time
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; The Time in Words
; 
; https://www.hackerrank.com/challenges/the-time-in-words
;

(def word-table
    [
    "X" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"
    "ten" "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen"
    "seventeen" "eighteen" "nineteen" "twenty" "twenty one"
    "twenty two" "twenty three" "twenty four" "twenty five"
    "twenty six" "twenty seven" "twenty eight" "twenty nine" "Y"])

(defn minutes-to-word
    "convert the number of minutes to word"
    [m]
    (if (> m 30)
        (minutes-to-word (- 60 m))
        (join "" [ (word-table m) " minute" (if (> m 1) "s" "")])))

(defn hours-to-word
    "convert the number of hours to word"
    [h]
    (word-table h))

(defn next-hour
    [h]
    (if (= h 12) 
        (hours-to-word 1)
        (hours-to-word (inc h))))

(defn to-words
    "convert time to word"
    ([s] (let [
            [h m] (map #(Integer/parseInt %) (split s #":"))
          ]
          (to-words h m)))
    ([h m] (let [
        hour-string (hours-to-word h)
        next-hour-string (next-hour h)
        minute-string (minutes-to-word m)
        ]
        (cond
            (= m 0)  (join " " [hour-string "o' clock"])
            (= m 15) (join " " ["quarter past" hour-string])
            (< m 30) (join " " [minute-string "past" hour-string])
            (= m 30) (join " " ["half past" hour-string])
            (= m 45) (join " " ["quarter to" next-hour-string])
            :else    (join " " [minute-string "to" next-hour-string])))))

(defn -main
  "Main program"
	[& args]
 	(let [
        h (Integer/parseInt (read-line))
        m (Integer/parseInt (read-line))
        ]
        (println (to-words h m))))

