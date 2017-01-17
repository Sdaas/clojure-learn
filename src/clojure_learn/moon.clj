(ns clojure-learn.moon
	(use [clojure.string :only (split triml lower-case)]
		 [clojure.set :only (union difference)]))

; Solution for ...
; https://www.hackerrank.com/challenges/journey-to-the-moon

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


(defn append-to-sets
	"Modify the sets based on the new input pair"
	; If the sets are {1 2 3} {4 5} 
	; for [3 4], the answer will be {1 2 3 4 5} merged the sets
	; for [5 6], the answer will be {1 2 3} {4 5 6} added to one set
	; for [6 7], the answer will be {1 2 3} {4 5} {6,7}
	[sets pair]
	;(println "append-to-sets called")
	;(println sets)
	;(println pair)

	(defn update-sets
		[set-x y]
		(let [
			removed-x (into #{} (remove #{set-x} sets)) ; remove the original set-a
			modified-x (conj set-x y)     ; update the set-a
			]
			(conj removed-x modified-x)))

	(let [
		a 	(first pair)
		b   (second pair)
		set-a (some #(when (contains? % a) %) sets) ; the set containing "a"
		set-b (some #(when (contains? % b) %) sets) ; the set containing "b"
		]
		(if (and (nil? set-a) (nil? set-b))
			(conj sets (into #{} pair)) ; no match. so create {a b} and append
			(if (and (not (nil? set-a)) (nil? set-b))
				(update-sets set-a b)
				(if (and (nil? set-a) (not (nil? set-b)))
					(update-sets set-b a)
					(if (= set-a set-b) ; both elements of pair were in the same set
						sets 			; nothing to do
										; both are present but in different sets
						(let [
							removed-a (into #{} (remove #{set-a} sets))
							removed-ab (into #{} (remove #{set-b} removed-a))
							merged-ab  (union set-a set-b)
							]
							(conj removed-ab merged-ab))))))))

(defn singletons
	"The number of singletons"
	[counts astronauts]
	; An astronaut will wither appear in "sets" (if there are other astronauts from that countr)
	; or be a singleton. This gives an easy way to compute number of singletons
 	(- astronauts (reduce + counts)))

(defn sets-from-pair-list
	[pair-list]
	(reduce #(append-to-sets %1 %2) #{} pair-list))

(defn ways-to-choose-pairs
	"compute the number of ways to choose pair"
	[counts astronauts]
	; counts : The number of astronauts in each group
	; s      : number of astronauts
	(if (empty? counts)
		(/ (* astronauts (dec astronauts)) 2)
		(let [
			a1    (first counts) ; astronauts in 1st group
			other (- astronauts a1) ; how many other astronauts
			pair1 (* a1 other)  ; ways to pair astronauts from 1st group  
			]
			(+ pair1 (ways-to-choose-pairs (rest counts) (- astronauts a1))))))


(defn process2
	"The main loop - version 2"
	[]
	(let [
		first-line (map #(Integer/parseInt %) (split (read-line) #"\s+"))
		astronauts (first first-line)
		pairs      (second first-line)
		pair-list  (for [temp (range pairs)]  (map #(Integer/parseInt %) (split (read-line) #"\s+") ) )
		sets       (sets-from-pair-list pair-list)
		counts     (map count sets)
		]
		;(println "histogram" hist)
		(println (ways-to-choose-pairs counts astronauts))))
		


