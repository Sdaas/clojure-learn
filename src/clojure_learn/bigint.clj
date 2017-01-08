(ns clojure-learn.bigint
	(:require [clojure.string :as str])
 	(:gen-class))

; Functions for big integer arithmetic - on arbitarily large 
; integer numbers. We represent a big int as a map. 
; 
; { :negative true  :number [0 5 4 0 2] }       // -20450
; { :negative false  :number [1023] }       	// 3201
;
; :negative : set to true for negative numbers. false otherwise
; :number   : vector where the nth index represents  10^n
;
; POSITIVE and NEGATIVE numbers
; ZERO
; ADDITION, SUBTRACTION, MULTIPLICATION, DIV, MOD
; EQUALITY, GREATER THAN, SMALLER THAN
;

; convert an int to bigint

; convert a string to bigint
; TODO error handling not included here
; See https://clojure.github.io/clojure/clojure.string-api.html
(defn string2bigint
	"Convert a string to bigint"
	[s]

	(defn string2intvec
		"Convert a string (without any sign) to a vector of int"
		[s]
		(let [ 
				strseq (map str (reverse (seq s))) ; should give ("1" "0" "2")
				intseq (map #(Integer/parseInt %) strseq)  ; gives (1 0 2) <- list
				intvec (into [] intseq)  ; convert this to vector [1 0 2]
		     ]
		     intvec))
	{:negative false :number (string2intvec s)})

; convert a bigint to a string

; add two bigint

; subtract bigint
