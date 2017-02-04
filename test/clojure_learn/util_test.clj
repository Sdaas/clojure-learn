(ns clojure-learn.util-test
  (:require [clojure.test :refer :all]
            [clojure-learn.util :refer :all]))

;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for util.clj
;	lein test clojure-learn.util-test
;
;To run only a specific test
;   lein test :only clojure-learn.util-test/foo
;

(deftest subsets-test
  (testing "generate all the subsets"
    (is (= #{#{}} (subsets #{})))   ; null set has only one subset
    (is (= #{#{} #{10}} (subsets #{10})))
    (is (= #{#{} #{10} #{20} #{10 20}} (subsets #{10 20})))
    (is (= #{#{} #{10} #{20} #{30} #{10 20} #{20 30} #{10 30} #{10 20 30}} (subsets #{10 20 30})))))

(deftest pairs-test
  (testing "generate all the pairs"
    (is (= #{ #{1 2}} (pairs #{1 2})))
    (is (= #{ #{1 2} #{1 3} #{2 3}} (pairs #{1 2 3})))
    (is (= #{ #{1 2} #{1 3} #{1 4} #{2 3} #{2 4} #{3 4}} (pairs #{1 2 3 4})))))
