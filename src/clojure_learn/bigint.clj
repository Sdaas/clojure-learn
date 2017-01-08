(ns clojure-learn.bigint
	(:require [clojure.string :as str])
 	(:gen-class))

; Functions for big integer arithmetic - on arbitarily large 
; integer numbers
;
; We represent a bigint as a vector where the nth index
; represents the 10^n. In this example, the 20450 would
; be represented as [0 5 4 0 2]
;
; POSITIVE and NEGATIVE numbers
; ZERO
; ADDITION, SUBTRACTION, MULTIPLICATION, DIV, MOD
; EQUALITY, GREATER THAN, SMALLER THAN
;

; convert an int to bigint

; convert a string to bigint
; TODO error handling not included here
; TODO add support for negative numbers
; See https://clojure.github.io/clojure/clojure.string-api.html
(defn string2bigint
	"Convert a string to bigint"
	[s]
	(let [ 
			strseq (map str (reverse (seq s))) ; should give ("1" "0" "2")
			intseq (map #(Integer/parseInt %) strseq)  ; gives (1 0 2) <- list
			intvec (into [] intseq)  ; convert this to vector [1 0 2]
	     ]  
	    ;(println intvec)
	    intvec))

; convert a bigint to a string

; add two bigint

; subtract bigint

