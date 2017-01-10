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

(defn- -trim
	"Remove any unncessary leading zeros"
	[v1]
	(if (= nil (last v1))
		[0]
		(if (= 0 (last v1))
			(-trim (pop v1))
			v1)))

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
(defn is-zero?
	"Return true if the number is zero, false otherwise"
	[n]
	(let
		[
			zn ((zero) :number)
			nn (n :number)
		]
		(= 0 (compare zn nn))))


; test for negative number
(defn negative?
	"Return true if this is a negative number, false otherwise"
	[n]
	(= true (n :negative)))

; test for negative number
(defn positive? 
	"Return true if this is a positive number, false otherwise"
	[n]
	(not (negative? n)))

; test for equality
(defn equal?
	"Returns true if two numbers are equal, false otherwise"
	[n1 n2]
	; first test for zero. This is a special case since "positive zero" == "negative zero"
	(if (and (is-zero? n1) (is-zero? n2))
		true
		(if (not= (n1 :negative) (n2 :negative))
			false	; if the signs are not same, then obviously not equal
			(= 0 (compare (n1 :number) (n2 :number)))))) ; compare() returns 0/1 so need to convert this true/false

; test for greater than
(defn greater?
	"Returns true if n1 > n2, false otherwise"
	[n1 n2]
	(let [
		cmp (compare (n1 :number) (n2 :number))
		both_positive ( and (positive? n1) (positive? n2))
		both_negative ( and (negative? n1) (negative? n2))
		n1_positive   ( and (positive? n1) (negative? n2))
		n2_positive   ( and (negative? n1) (positive? n2))
		]

		(if n1_positive
			true
			(if n2_positive
				false
				(if both_positive
					(= 1 cmp)
					(= -1 cmp))))))

; test for less than
(defn less?
	"Returns true if n1 < n2, false otherwise"
	[n1 n2]
	; if n1 != n2  AND n1 is not greater than n2, then n1 is less than n2
	(and (not (equal? n1 n2)) (not (greater? n1 n2))))

; absolute value
(defn abs
	"returns the absolute value of the number"
	[n]
	{:negative false :number (n :number)})

; Private function - add two vectors of numbers without paying attention to the sign
(defn- -unsigned-add
	"Add two vectors"
	[v1 v2]

	(defn accumulate
		[acc carry v1 v2]
		(println "accumulate called")
		(println "v1 = " v1)
		(println "v2 = " v2)
		(println "ac = " acc)
		(println "carry = " carry)
		(if (and (empty? v1) (empty? v2)) 
			(if (= 0 carry)
				acc
				(conj acc carry))
			(let [
				n1  (or (first v1) 0)
				n2  (or (first v2) 0)
				sum (+ carry n1 n2)
				v   (rem sum 10)
				new_carry (quot sum 10)
				new_acc (conj acc v)
				]
				(recur new_acc new_carry (rest v1) (rest v2))))) ; use recur here ? 

	; main body
	(accumulate [] 0 v1 v2))


; Private function - subtract two vectors of numbers without paying attention to the sign
(defn- -unsigned-subtract
	"Subtract two vectors"
	[v1 v2]     ; assumes that v1 > v2


	(defn borrow
		[v1]
		(if (= 0 (second v1))
			(borrow (cons (first v1) (borrow (rest v1))))
			(let [
				tail (rest (rest v1))
				n1   ( + (first v1) 10)
				n2   (dec (second v1))
				]
				(cons n1 (cons n2 tail)))))

	(defn accumulate
		[acc v1 v2]
		(println "accumulate called")
		(println "v1 = " v1)
		(println "v2 = " v2)
		(println "ac = " acc)
		(if (and (empty? v1) (empty? v2)) 
			acc
			(let [
				n1  (or (first v1) 0)
				n2  (or (first v2) 0)
				do_borrow (< n1 n2)
				]
				(if do_borrow
					; borrowing
					(accumulate acc (borrow v1) v2)
					; no borrowing needed. So simple case
					(let [
						diff (- n1 n2)
						new_acc (conj acc diff)
						]
						(recur new_acc (rest v1) (rest v2)))))))

	; main body
	(-trim (accumulate [] v1 v2)))


; add two bigint
(defn add
	"Add two numbers"
	[n1 n2]
	(let [
		v1	(n1 :number)
		v2  (n2 :number)
		]
		(println "** add called **")
		(if (and (positive? n1) (positive? n2))
				{:negative false :number (-unsigned-add v1 v2)}
				(if (and (negative? n1) (negative? n2))
					{:negative true :number (-unsigned-add v1 v2)}
					(if (and (positive? n1) (negative? n2))
						; n1 > 0, n2 < 0
						(if (greater? (abs n1) (abs n2))
							{:negative false :number (-unsigned-subtract v1 v2)}
							{:negative true  :number (-unsigned-subtract v2 v1)})
						; n1 < 0 n2 > 0
						(if (greater? (abs n2) (abs n1))
							{:negative false :number (-unsigned-subtract v2 v1)}
							{:negative true  :number (-unsigned-subtract v1 v2)}))))))


; subtract bigint
(defn subtract
	"Subtract n2 from n1"
	[n1 n2]
	(let [
		v1	(n1 :number)
		v2  (n2 :number)
		result (-unsigned-subtract v1 v2)
		]
		(println "** subtract called **")
		{:negative false :number result}
		))





