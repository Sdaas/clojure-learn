(ns clojure-learn.camelcase
	(use [clojure.string :only (split triml lower-case)]))

; camelcase
; Soluton for https://www.hackerrank.com/challenges/camelcase
;
; given a camelCase word (e.g, potusFortyFourRocks), print the number of words
; in string (in this example 4). 
;

; How keep-index works ..
; (keep-index f coll) This returns a sequence non-nil results of (f index item)
; See http://clojure.github.io/clojure/clojure.core-api.html#clojure.core/keep-indexed
;
; We define 
(defn index-if-uppercase 
	"return the indices of the uppercase characters"
	[str]

	(defn myfunc
		"returns the index if the character is uppercase, nil otherwise"
		[index item]
		(when (Character/isUpperCase item) index))

   (keep-indexed myfunc (seq str)))


(defn words 
	"convert a camel case string into the constituent words"
	[str]
	(if (= 0 (count str))
		`() ; return an empty list
		(let [
			indices (index-if-uppercase str)
			i1      (cons 0 indices)
			i2      (conj (into [] indices) (count str))
			]
			(map #(subs str %1 %2) i1 i2))))
