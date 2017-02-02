;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for cloudjump.clj
;	lein test clojure-learn.cloudjump-test
;
;To run only a specific test
;   lein test :only clojure-learn.cloudjump-test/foo
;

(ns clojure-learn.cloudjump-test
  (:require [clojure.test :refer :all]
            [clojure-learn.cloudjump :refer :all]))


(deftest visited-test
  (testing "simple case - given n and k compute the list of visited nodes"
    (is (= [2 4 6 0] (visited 8 2)))
    (is (= [3 1 4 2 0] (visited 5 3))))
  (testing "where k is a multiple of n"
    (is (= [0] (visited 8 8)))
    (is (= [0] (visited 5 100))))
  (testing "where k is greater than n"
    (is (= [2 4 1 3 0] (visited 5 7)))
    (is (= [1 2 3 4 0] (visited 5 6)))))

(deftest energy-test
  (testing "compute the amount of energy spent in visiting these nodes"
    (is (= 8 (energy [2 4 6 0] [0 0 1 0 0 1 1 0])))))