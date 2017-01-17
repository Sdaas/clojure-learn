;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for moon.clj
;	lein test clojure-learn.moon-test
;
;To run only a specific test
;   lein test :only clojure-learn.moon-test/string2bigint-test
;

(ns clojure-learn.moon-test
  (:require [clojure.test :refer :all]
            [clojure-learn.moon :refer :all]))


(deftest append-test
	(testing "first of pair found in a single set"
		(is (= #{ #{1 2 3 7} #{4 5}} (append-to-sets #{ #{1 2 3} #{4 5}} [2 7] ))))
	(testing "second of pair found in a single set"
		(is (= #{ #{1 2 3} #{4 5 8}} (append-to-sets #{ #{1 2 3} #{4 5}} [8 4] ))))
	(testing "both found in a single set"
			(is (= #{ #{1 2 3} #{4 5}} (append-to-sets #{ #{1 2 3} #{4 5}} [1 3] ))))
	(testing "both found in two different sets"
		(is (= #{ #{1 2 3 4 5} #{6 7}} (append-to-sets #{ #{1 2 3} #{4 5} #{6 7}} [2 4] ))))
	(testing "no match"
		(is (= #{ #{1 2 3} #{4 5} #{6 7}} (append-to-sets #{ #{1 2 3} #{4 5}} [6 7] )))))


(deftest singletons-test
	(testing "singletons count"
		(is (= 2 (singletons `(2 2) 6)))
		(is (= 1 (singletons `(3 2) 6)))
		(is (= 0 (singletons `(3 3) 6)))
		(is (= 6 (singletons `() 6)))))

(deftest sets-from-pairlist-test
	(testing "create the sets given pairlist"
		; { 0 1 2 4 6 8  9} {3 5}
		(is (= #{ #{0 1 2 4 6 8 9} #{3 5}} (sets-from-pair-list [ [0 2] [1 8] [1 4] [2 8] [2 6] [3 5] [6 9]] )))
		; { 0 1 2} {3 4 5} {6 7} {8 9} 
		(is (= #{#{0 1 2} #{3 4 5} #{6 7} #{8 9}} (sets-from-pair-list [[0 1][0 2][3 4][3 5][6 7][8 9]] )))
		; {1 2} {3 4} 
		(is (= #{ #{1 2} #{3 4}} (sets-from-pair-list [[1 2] [3 4]] )))
))

(deftest ways-test
	(testing "no singletons"
		(is (= 30 (ways-to-choose-pairs `(5 6) 11))))
	(testing "only singletons"
		(is (= 10 (ways-to-choose-pairs `() 5)))
		(is (= 0  (ways-to-choose-pairs `() 1)))
		(is (= 1  (ways-to-choose-pairs `() 2))))
	(testing "with singletons"
		; groups : 7 2 1 => 7x3 + 2x1  = 21 + 2 + 23
		(is (= 23 (ways-to-choose-pairs `(7 2) 10)))  ; groups : 7 2 1
		(is (= 43 (ways-to-choose-pairs `(2 2) 10)))
		(is (= 27 (ways-to-choose-pairs `(3 4) 9)))
		(is (= 27 (ways-to-choose-pairs `(4 3) 9)))
		(is (= 4999949998 (ways-to-choose-pairs `(2 2) 100000))) 
		; groups ; 3 3 3 => 3x6 + 3x3  = 18 + 9 = 27
		(is (= 27 (ways-to-choose-pairs `(3 3 3) 9))))); groups : 3 3 3

(deftest temp
	(testing "placeholder for a single test"
		(is (= 21 (ways-to-choose-pairs `(3 4) 9)))

	)) ; all singletons

