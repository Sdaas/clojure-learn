;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for try.clj
;	lein test clojure-learn.try-test
;
;To run only a specific test
;   lein test :only clojure-learn.try-test/addtest1
;

(ns clojure-learn.try-test
  (:require [clojure.test :refer :all]
            [clojure-learn.try :refer :all]))

(deftest addtest1
  (testing "I pass."
    (is (= 10 (myadd 5 5)))))

(deftest addtest2
  (testing "FIXME, I fail."
    (is (= 1 (myadd 5 5)))))