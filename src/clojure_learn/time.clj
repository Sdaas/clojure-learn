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

(defn to-words
    "convert time to word"
    [s]
    (let [
        [h m] (map #(Integer/parseInt %) (split s #":"))
        hstring (hours-to-word h)
        hstring2 (hours-to-word (inc h))
        mstring (minutes-to-word m)
        ]
        (cond
            (= m 0)  (join " " [hstring "o' clock"])
            (< m 30) (join " " [mstring "past" hstring])
            (= m 30) (join " " ["half past" hstring])
            :else    (join " " [mstring "to" hstring2]))))

(defn -main
  "Main program"
	[& args]
 	(let [
        [n k]  (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        cost   (into [] (map #(Integer/parseInt %) (split (read-line) #"\s+")))
        ]
        (println "foo")))

