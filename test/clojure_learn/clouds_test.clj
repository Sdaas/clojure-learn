;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for clouds.clj
;	lein test clojure-learn.clouds-test
;
;To run only a specific test
;   lein test :only clojure-learn.clouds-test/foo
;

(ns clojure-learn.clouds-test
  (:require [clojure.test :refer :all]
            [clojure-learn.clouds :refer :all]))

(deftest paths2-test
	(testing "two nodes"
		(is (= #{'(1 2)} (paths [1 2] [false false]))) ; only one path
		(is (= #{} (paths [1 2] [false true])))        ; no path
		(is (= #{} (paths [1 2] [true false])))))     ; no path

(deftest paths3-test
	(testing "three nodes"
		(is (= #{'(1 3) '(1 2 3)} (paths [1 2 3] [false false false])))
		(is (= #{} (paths [1 2 3] [false true true])))  ; no path since last is a thunder cloud 
		(is (= #{'(1 3)} (paths [1 2 3] [false true false])))  
		(is (= #{} (paths [1 2 3] [false true true]))) 
		(is (= #{} (paths [1 2 3] [true false false]))))); no paths since first is a thunder cloud

(deftest paths-test
	(testing "more than three nodes"
		(is (= #{'(1 2 4 5 7)} (paths [1 2 3 4 5 6 7] [false false true false false true false])))))

(deftest minimum-test
	(testing "minimum steps"
		(is (= 1 (minimum-steps [0 0])))
		(is (= 1 (minimum-steps [0 0 0])))
		(is (= 2 (minimum-steps [0 0 0 0])))  
		(is (= 3 (minimum-steps [0 1 0 0 0 0])))
        (is (= 4 (minimum-steps [0 0 1 0 0 1 0])))
		(is (= 3 (minimum-steps [0 0 0 0 1 0])))
	))
