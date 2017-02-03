;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for prisoner.clj
;	lein test clojure-learn.prisoner-test
;
;To run only a specific test
;   lein test :only clojure-learn.prisoner-test/foo
;

(ns clojure-learn.prisoner-test
  (:require [clojure.test :refer :all]
            [clojure-learn.prisoner :refer :all]))

(deftest poison-test
  (testing "calculate the id of the posoned prisoner"
    (is (= 2 (poisoned-prisoner [5 2 1])))
    (is (= 1 (poisoned-prisoner [7 13 3])))))



