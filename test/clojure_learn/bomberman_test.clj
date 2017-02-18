(ns clojure-learn.bomberman-test
  (:require [clojure.test :refer :all]
            [clojure-learn.bomberman :refer :all]))

;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for bomberman.clj
;	lein test clojure-learn.bomberman-test
;
;To run only a specific test
;   lein test :only clojure-learn.bomberman-test/foo
;

(deftest tick-test
  (testing "testing the tick function"
  (is (= '(0 0 1 2 3 4) (tick '(0 1 2 3 4 5))))))

(deftest place-bombs-test
  (testing "place bombs in all places wehre ther is no bomb"
    (is (= '(3 1 3 2 3 3 3 4) (place-bombs '(0 1 0 2 0 3 0 4))))))

; All the following tests are based on the following 4 x 5 matrix
; 0  1  2  3  4
; 5  6  7  8  9
;10 11 12 13 14
;15 16 17 18 19

(deftest first-cell-in-row-test
  (testing "test if this is first cell in the row"
    (is (= true (first-cell-in-row? 0 5)))
    (is (= false (first-cell-in-row? 6 5)))
    (is (= true (first-cell-in-row? 10 5)))
    (is (= false (first-cell-in-row? 19 5)))))

(deftest last-cell-in-row-test
  (testing "test if this is last cell in the row"
    (is (= true (last-cell-in-row? 4 5)))
    (is (= false (last-cell-in-row? 8 5)))
    (is (= true (last-cell-in-row? 14 5)))
    (is (= false (last-cell-in-row? 15 5)))))

(deftest cell-in-first-row-test
  (testing "test if the cell is in the first row"
    (is (= true (cell-in-first-row? 1 4 )))
    (is (= true (cell-in-first-row? 3 4 )))
    (is (= false (cell-in-first-row? 5 4 )))
    (is (= false (cell-in-first-row? 14 4 )))
    (is (= false (cell-in-first-row? 15 4 )))
    (is (= false (cell-in-first-row? 18 4 )))))

(deftest cell-in-last-row-test
  (testing "test if the cell is in the last row"
    (is (= false (cell-in-last-row? 1 4 5)))
    (is (= false (cell-in-last-row? 3 4 5)))
    (is (= false (cell-in-last-row? 5 4 5)))
    (is (= false (cell-in-last-row? 14 4 5)))
    (is (= true (cell-in-last-row? 15 4 5)))
    (is (= true (cell-in-last-row? 18 4 5)))))

