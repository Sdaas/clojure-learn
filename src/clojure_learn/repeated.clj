(ns clojure-learn.repeated
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Occurrences of a letter in an infinitely repeated string
; 
; https://www.hackerrank.com/challenges/repeated-string
;
; The infinite string can be described as N occurences of the string
; plus a partial string such that its length is equal ton

(defn occurences
    "The occurences of a in the string"
    [str n]
    100
)

(defn -main
  "Main program"
	[& args]
 	(let [
        str  (read-line)   ; the string to be repeated 
        n    (read-line)   ; the length to search in
        ]
        (println (occurences str n))))

