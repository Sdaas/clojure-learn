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
	"Convert a string to bigint. example -1234 or 1234"
	[s]

	; internal utility function - that converts a string (without sign) 
	; into a vector where index n represents the 10^n position
	(defn string2intvec
		"Convert a string (without any sign) to a vector of int"
		[s]
		(let [ 
				strseq (map str (reverse (seq s))) ; should give ("1" "0" "2")
				intseq (map #(Integer/parseInt %) strseq)  ; gives (1 0 2) <- list
				intvec (into [] intseq)  ; convert this to vector [1 0 2]
		     ]
		     intvec))
	; main body
	(let [ 
			negative (= "-" (subs s 0 1)) ; set to true/false if the leading char is a "-"
			s2 (if negative (subs s 1) s) ; if negative number, then ignore the sign
		]
		{:negative negative :number (string2intvec s2) }))
	

; convert a bigint to a string
(defn bigint2string
	"Convert a bigint to string"
	[bigint]

	;main body of function
	(let [
		strseq (map str (reverse (bigint :number)))   ; convert the number part into a string
		sign   (if (bigint :negative) "-" "")         ; determine if we need to put a "-" sign in front      
		]
		(clojure.string/join "" (conj strseq sign))))

; return zero
(defn zero
	"Returns zero"
	[]
	{:negative false :number [0]})

; test for zero
; works both for "negative zero" and "positive zero"
(defn zero?
	"Return true if the number is zero, false otherwise"
	[n]
	(let
		[
			zn ((zero) :number)
			nn (n :number)
		]
		(= 0 (compare zn nn))))

; test for equality
(defn equal?
	"Returns true if two numbers are equal, false otherwise"
	[n1 n2]
	; first test for zero. This is a special case since "positive zero" == "negative zero"
	(if (and (zero? n1) (zero? n2))
		true
		(if (not= (n1 :negative) (n2 :negative))
			false	; if the signs are not same, then obviously not equal
			(= 0 (compare (n1 :number) (n2 :number)))))) ; compare() returns 0/1 so need to convert this true/false

; add two bigint

; subtract bigint
