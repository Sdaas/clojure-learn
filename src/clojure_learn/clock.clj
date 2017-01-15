(ns clojure-learn.clock
	(use [clojure.string :only (split triml lower-case)]))

; Solution for HackerRank problem at
; https://www.hackerrank.com/challenges/time-conversion

; Convert the time from a 12hours format (07:05:45PM)
; to a 24-hour format (19:07:45)

(defn military_time
  "Convert time from a 12-hour format to a 24-hour format"

  [s] ; time as string

  (defn convert_hour
    "converts the hour from 12-hour format to 24 hour format"
    [hour pm]
    ; if 12am return 0
    ; if 12pm return 12
    ; else if am return hour
    ; else return hour + 12
    (if (= hour 12)
        (if pm 12 0) ; for 12pm return 12. For 12am return 0
        (if pm (+ 12 hour) hour )))

  (let [
    v (re-find #"(\d+):(\d+)(:(\d+))?(\s*)(AM|PM)" s)
    hour (Integer/parseInt (v 1))
    min  (Integer/parseInt (v 2))
    secstr (v 4)
    sec  (if (= secstr nil) 0 (Integer/parseInt secstr))
    pm   (= (lower-case (v 6)) "pm")
    milhour (convert_hour hour pm)
    ]
    (clojure.string/join ":" [(format "%02d" milhour) (format "%02d" min) (format "%02d"  sec)])))
