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
		(is (= #{ #{3} #{5}} (missing-sets #{ #{1 2} #{0 4}} 6)))))

(deftest singletons-test
	(testing "singletons count"
		(is (= 2 (singletons-count #{ #{1 2} #{0 4}} 6)))
		(is (= 1 (singletons-count #{ #{1 2 3} #{5 4}} 6)))
		(is (= 0 (singletons-count #{ #{1 2 3} #{0 4 5}} 6)))
		(is (= 6 (singletons-count #{} 6)))))

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

(deftest histogram-test
	(testing "zero singletons"
		(is (= {2 3 10 2 4 1 1 0} (histogram `(2 2 2 10 10 4) 0)))
		(is (= {1 0} (histogram `() 0))))
	(testing "non-zero singletons"
		(is (= {2 3 10 2 4 1 1 6} (histogram `(2 2 2 10 10 4) 6)))
		(is (= {1 6} (histogram `() 6)))))

(deftest choose-test
 	(testing "ways to choose astronauts from countries"
  		(is (= 0 (ways-to-choose-pair `())))
  		(is (= 0 (ways-to-choose-pair `(5))))
  		(is (= 4 (ways-to-choose-pair `(2 2))))
  		(is (= 26 (ways-to-choose-pair `(4 3 2))))
		(is (= 23 (ways-to-choose-pair `(7 2 1))))))

(deftest choose2-test
 	(testing "ways to choose astronauts from countries"
  		(is (= 10 (ways-to-choose-pair2 `() 5 )))
  		(is (= 20 (ways-to-choose-pair2 `(2) 5 )))
  		(is (= 24 (ways-to-choose-pair2 `(2 3) 3 )))
  		(is (= 56 (ways-to-choose-pair2 `(4 3 2) 3)))
		(is (= 23 (ways-to-choose-pair2 `(7 2 1) 0)))))




