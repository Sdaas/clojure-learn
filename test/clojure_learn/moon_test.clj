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

(deftest missing-test
	(testing "missing"
		(is (= #{ #{3} #{5}} (missing-sets #{ #{1 2} #{0 4}} 6)))
		)
	)

(deftest merge-test
	(testing "merge-test"
		(is (= #{ #{1 2 3}} (merge-sets #{ #{1 2} #{2 3} } 2 3)))
		(is (= #{ #{1} #{2 3} #{4}} (merge-sets #{ #{1} #{2} #{3} #{4}} 2 3)))))

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

	; If the sets are {1 2 3} {4 5} 
	; for [3 4], the answer will be {1 2 3 4 5} merged the sets
	; for [5 6], the answer will be {1 2 3} {4 5 6} added to one set
	
(deftest choose-test
 	(testing "ways to choose astronauts from countries"
  		(is (= 0 (ways-to-choose-pair `())))
  		(is (= 0 (ways-to-choose-pair `(5))))
  		(is (= 4 (ways-to-choose-pair `(2 2))))
  		(is (= 26 (ways-to-choose-pair `(4 3 2))))
		(is (= 23 (ways-to-choose-pair `(7 2 1))))))