(ns clojure-learn.absolute-permutation-test
  (:require [clojure.test :refer :all]
            [clojure-learn.absolute-permutation :refer :all]))

;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for absolute-permutation.clj
;	lein test clojure-learn.absolute-permutation-test
;
;To run only a specific test
;   lein test :only clojure-learn.absolute-permutation-test/foo
;

(deftest smallest-test
  (testing "smallest permultation test"
    (is (= [2 1] (smallest [] #{1 2} 1 1)))
    (is (= [1 2 3] (smallest [] #{1 2 3} 1 0)))
    (is (nil? (smallest [] #{1 2 3} 1 1)))
    (is (nil? (smallest [] #{1 2 3} 1 2)))))


