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

(deftest solution-test
  (testing "determine if a solution exists"
    (is (solution? 10 0))  ; k = 0
    (is (solution? 12  1))
    (is (solution? 12 2))
    (is (solution? 12 3))
    (is (not (solution? 12  5)))
    (is (not (solution? 12  5)))
    (is (solution? 12 6))
    (is (not (solution? 12  7)))
    (is (not (solution? 12  8)))
    ))

(deftest smallest-test
  (testing "smallest permultation test"
    (is (= [2 1] (smallest [] #{1 2} 1 1)))
    (is (= [1 2 3] (smallest [] #{1 2 3} 1 0)))
    (is (nil? (smallest [] #{1 2 3} 1 1)))
    (is (nil? (smallest [] #{1 2 3} 1 2)))))

(deftest zero-k-test
  (testing "case when k = 0 "
    (is (= [1 2 3 4 5] (smallest 5 0)))
    )
  )

