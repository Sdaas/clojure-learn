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


(defn create-sets
	"Create the sets from the pair list"
	[pair-list]


	(defn inner-create-sets
		[astro-map group-map pair-list country-index]
		; astro : map of astro # to country #
		; group : map of country # to set whose members are astros
		;(println "** called **")
		;(println "astro-map = " astro-map)
		;(println "group-map = " group-map)
		;(println "pair-list = " pair-list)
		(if (empty? pair-list)
			(into #{} (vals group-map)) ; return the values of the group-map as a set
			(let [
				p1  (first pair-list)
				a   (first p1)
				b   (second p1)
				isa (some? (astro-map a))  ; is "a" present in  map
				isb (some? (astro-map b))  ; is "b" present in  map
				]

				(cond
					(and (not isa) (not isb))

						(do
						; neither a nor b are present in the astro-map. 
						; so we are encountering both for first time
						;(println "neither a/b present in astro-map")
						(let [
							cc  (inc country-index) 
							new-astro (conj (conj astro-map {a cc}) {b cc})
							new-group (conj group-map {cc #{a b}})
							]
							(recur new-astro new-group (rest pair-list) cc)))
					
					(and isa (not isb))
						(do
						; a is present but not b
						;(println "only a is present in astro-map")
						(let [
							cc  (inc country-index)
							country-a (astro-map a)
							new-astro (conj astro-map {b country-a})
							country-set (group-map country-a)     ; get the set for country-a
							new-country-set  (conj country-set b) ; add b to that set
							new-group (conj group-map {country-a new-country-set})
							]
							(recur new-astro new-group (rest pair-list) cc)))
					
					(and (not isa) isb)
						(do
						; b is present but not a
						;(println "only b is present in astro-map")
						(let [
							cc  (inc country-index)
							country-b (astro-map b)
							new-astro (conj astro-map {a country-b})
							country-set (group-map country-b)     ; get the set for country-a
							new-country-set  (conj country-set a) ; add b to that set
							new-group (conj group-map {country-b new-country-set})
							]
							(recur new-astro new-group (rest pair-list) cc)))
					
					(and isa isb)
						(do
						; both a and b are present
						;(println "both a and b is present in astro-map")
						(let [
							cc  (inc country-index)
							country-a  (astro-map a)    
							country-b  (astro-map b)
							set-a      (group-map country-a)
							set-b      (group-map country-b) 
							new-set-a  (clojure.set/union set-a set-b) 
							new-group  (conj (dissoc group-map country-b) {country-a new-set-a})
							tmp-astro (apply dissoc astro-map set-b) ; REMOVE the entries for astronauts in set b
 							new-astro (reduce #(conj %1 {%2 country-a}) tmp-astro set-b)
							]
							(recur new-astro new-group (rest pair-list) cc)))
				))))

	(inner-create-sets {} {} pair-list 0)	
)

(defn singletons
	"The number of singletons"
	[counts astronauts]
	; An astronaut will wither appear in "sets" (if there are other astronauts from that countr)
	; or be a singleton. This gives an easy way to compute number of singletons
 	(- astronauts (reduce + counts)))

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
		;sets       (time (sets-from-pair-list pair-list))
		sets        (time (create-sets pair-list))
		counts     (map count sets)
		]
		;(println "histogram" hist)
		(println (ways-to-choose-pairs counts astronauts))))
		


