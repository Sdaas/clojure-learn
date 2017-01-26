;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for divisiblepairs.clj
;	lein test clojure-learn.divisiblepairs-test
;
;To run only a specific test
;   lein test :only clojure-learn.divisiblepairs-test/foo
;

(ns clojure-learn.divisiblepairs-test
  (:require [clojure.test :refer :all]
            [clojure-learn.divisiblepairs :refer :all]))

(deftest pair1-test
	(testing "Create pairs from a number and list"
        (is (= '( (1 4) (1 3) (1 2)) (pair-one 1 '(2 3 4))))
    	(is (= '() (pair-one 10 '())))
		(is (= '( (1 2) (1 2) (1 2)) (pair-one 1 '(2 2 2))))))

(deftest pair-test
  (testing "create pairs from list of three numbers"
    (is (= '((1 3) (1 2) (2 3)) (pairs '(1 2 3))))))