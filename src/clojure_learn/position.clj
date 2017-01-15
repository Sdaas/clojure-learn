(ns clojure-learn.position
	(use [clojure.string :only (split triml lower-case)]))

; Solution to
; https://www.hackerrank.com/challenges/tutorial-intro
;
; Given a number n, find its position in a vector

(defn position
	"Find the position of the number in the given list"
	[n v]

	(defn inner
		[index data]
		(if (= 0 (count data))
			-1
			(if (= n (first data))
				index
				(recur (inc index) (rest data)))))

	(inner 0 v))


