(ns clojure-learn.moon
	(use [clojure.string :only (split triml lower-case)]))

; Solution for ...
; https://www.hackerrank.com/challenges/journey-to-the-moon
;

; There are two parts to the solution
; (a) Find out of many countries there are, and who are the astronauts
; in each country. In other words, how many sets are there, and what
; are the members in each set
;
; (b) Given a collection of sets, and the number of elements in each set
; figure out the ways to select 2 elements so that both elements are
; not from the same set
;
; Example
; 6 
; 0 1
; 1 2
; 1 3
; 4 5
;
; There are two sets {0,1,2,3} and {4,5}. So there are 4x2 ways to choose
;
; There is a catch here - what happens if a number is not mentioned ? 
;
; Example
;
; 5
; 0 1
; 1 2
; 3 5
; What about "astronaut #4" ? Turns out he is in his own set. So in
; this case, there are _THREE_ sets {0 1 2} {3 5} {4}

(defn ways-to-choose-pair
	"given the number of astronauts from each country"
	[countries]
	; The list contains the number of astronauts in each set
	; Suppose the list is A B C D E .... 
	; Then soln(A B C D E) = A*B + A*C + A*D + A*E + soln(B C D E)
	; Obviously soln(E) = 0
	(if (< (count countries) 2 )
		0
		(let [
			tail (rest countries)
			part1 (* (first countries) (reduce + tail))
			part2 (ways-to-choose-pair tail)
			]
			(+ part1 part2))))


