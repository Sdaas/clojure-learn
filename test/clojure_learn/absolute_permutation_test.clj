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

(deftest zero-k-test
  (testing "case when k = 0 "
    (is (= [1 2 3 4 5] (smallest 5 0)))
    ))

(deftest smoke-test
  (testing "smallest permultation test"
    (is (= [2 1] (smallest 2 1)))
    (is (= [1 2 3] (smallest 3 0)))
    (is (nil? (smallest 3 1)))
    (is (nil? (smallest 3 2)))))

(deftest smallest-test2
  (testing "smallest permutation test"
    (is (= [2 1 4 3 6 5 8 7 10 9 12 11] (smallest 12 1)))
    (is (= [3 4 1 2 7 8 5 6 11 12 9 10] (smallest 12 2)))
    (is (= [4 5 6 1 2 3 10 11 12 7 8 9] (smallest 12 3)))
    (is (nil? (smallest 12 4)))
    (is (nil? (smallest 12 5)))
    (is (= [7 8 9 10 11 12 1 2 3 4 5 6] (smallest 12 6)))
    ;(is (nil? (smallest 86542 1)))   ;; stack overflowing in this case
    ))

(deftest chunk-test
  (testing "chunk-test for k=1"
    (is (= '(2 1) (process-chunk 0 1)))
    (is (= '(4 3) (process-chunk 1 1)))
    (is (= '(6 5) (process-chunk 2 1)))
    (is (= '(8 7) (process-chunk 3 1))))
  (testing "chunk-test for k=2"
    (is (= '(3 4 1 2) (process-chunk 0 2)))
    (is (= '(7 8 5 6) (process-chunk 1 2)))
    (is (= '(11 12 9 10) (process-chunk 2 2))))
  (testing "chunk-test for k=3"
    (is (= '(4 5 6 1 2 3) (process-chunk 0 3)))
    (is (= '(10 11 12 7 8 9) (process-chunk 1 3))))
  (testing "chunk-test for k=6"
    (is (= '(7 8 9 10 11 12 1 2 3 4 5 6) (process-chunk 0 6)))))
