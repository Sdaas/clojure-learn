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

(deftest create-sets-test
	(testing "disjoint"
		(is (= #{ #{0 1} #{2 3} #{4 5} } (create-sets [[0 1] [2 3] [4 5]] ))))
	(testing "one element in pair is present")
		(is (= #{ #{0 1} #{2 3 4} } (create-sets [[0 1] [2 3] [3 4]] )))
		(is (= #{ #{0 1} #{2 3 4} } (create-sets [[0 1] [2 3] [4 3]] )))
		(is (= #{ #{0 3 4} #{1 2 5} } (create-sets [[0 3] [1 2] [3 4] [2 5]] )))
	(testing "both elements present"
		(is (= #{ #{0 2 4 5}} (create-sets [[0 2] [4 5] [2 4]] )))
		(is (= #{ #{0 2 4 5}} (create-sets [[0 2] [4 5] [2 4]] ))))
	(testing "misc"
		; { 0 1 2 4 6 8  9} {3 5}
		(is (= #{ #{0 1 2 4 6 8 9} #{3 5}} (create-sets [ [0 2] [1 8] [1 4] [2 8] [2 6] [3 5] [6 9]] )))
		; { 0 1 2} {3 4 5} {6 7} {8 9} 
		(is (= #{#{0 1 2} #{3 4 5} #{6 7} #{8 9}} (create-sets [[0 1][0 2][3 4][3 5][6 7][8 9]] )))
		; {1 2} {3 4} 
		(is (= #{ #{1 2} #{3 4}} (create-sets[[1 2] [3 4]] )))))

(deftest singletons-test
	(testing "singletons count"
		(is (= 2 (singletons `(2 2) 6)))
		(is (= 1 (singletons `(3 2) 6)))
		(is (= 0 (singletons `(3 3) 6)))
		(is (= 6 (singletons `() 6)))))

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




