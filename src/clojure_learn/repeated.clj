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
    [str ch]
    (count (filter #(= ch %1) (seq str))))

(defn occurences-n
    "The occurences of the character ch in the first n letters of string"
    [str n ch]
    (if (= 0 n)
        0
        (let [
            len     (count str)
            full    (quot n len) ; number of full strings
            rel     (rem n len) ; length of the partial string
            partial-str (take rel str) ; the partial string
            c1      (occurences str ch)
            c2      (occurences partial-str ch)
            ]
            (+ (* full c1) c2))))


(defn -main
  "Main program"
	[& args]
 	(let [
        str  (read-line)   ; the string to be repeated 
        n    (read-string (read-line))   ; the length to search in
        ]
        (println (occurences-n str n \a))))

