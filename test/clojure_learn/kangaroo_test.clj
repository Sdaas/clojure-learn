;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for kangaroo.clj
;	lein test clojure-learn.kangaroo-test
;
;To run only a specific test
;   lein test :only clojure-learn.kangaroo-test/foo
;

(ns clojure-learn.kangaroo-test
  (:require [clojure.test :refer :all]
            [clojure-learn.kangaroo :refer :all]))

(deftest meet-test
  (testing "v1 not = v2"
    (is (= true (meet? 0 3 4 2)))
    (is (= false (meet? 0 2 5 3)))
    (is (= false (meet? 21 6 47 3)))
    (is (= true (meet? 10 3 10 3))))
  (testing "v1 = v2"
    (is (= false (meet? 10 3 30 3)))
    (is (= true (meet? 10 3 10 3)))))