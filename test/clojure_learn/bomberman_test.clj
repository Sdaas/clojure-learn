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

(deftest next-cell-bomb-test
  (testing "is the next cell a bomb - end of row"
    (let [
          data [0 0 0 1, 0 0 0 0]
          R 2
          C 4]
      (is (not (next-cell-bomb? data 3 R C)))
      (is (not (next-cell-bomb? data 7 R C)))))
  (testing "is the next cell a bomb - start of row"
    (let [
          data [0 1 0 0, 0 0 0 0, 0 1 0 0 ]
          R 3
          C 4]
      (is (next-cell-bomb? data 0 R C))
      (is (not (next-cell-bomb? data 4 R C)))
      (is (next-cell-bomb? data 8 R C))))
  (testing "is the next cell a bomb - middle of row"
    (let [
          data [0 0 1 0, 0 0 0 1, 0 0 0 0 ]
          R 3
          C 4]
      (is (next-cell-bomb? data 1 R C))
      (is (not (next-cell-bomb? data 5 R C)))
      (is (not (next-cell-bomb? data 8 R C)))))
  )

(deftest prev-cell-bomb-test
  (testing "is the previous cell a bomb - start of row"
    (let [
          data [0 0 0 0, 1 0 0 0]
          R 2
          C 4]
      (is (not (prev-cell-bomb? data 0 R C)))
      (is (not (prev-cell-bomb? data 4 R C)))))
  (testing "is the previous cell a bomb - middle of row"
    (let [
          data [0 0 0 0, 1 0 0 0, 0 0 0 0]
          R 3
          C 4]
      (is (not (prev-cell-bomb? data 1 R C)))
      (is (prev-cell-bomb? data 5 R C))
      (is (not (prev-cell-bomb? data 9 R C)))))
  )

(deftest top-cell-bomb-test
  (testing "is the cell in the top cell a bomb - first row"
    (let [
          data [0 1 0 1, 0 0 0 0]
          R 2
          C 4]
      (is (not (top-cell-bomb? data 0 R C)))
      (is (not (top-cell-bomb? data 3 R C)))))
  (testing "is the cell in the top cell a bomb - other rows"
    (let [
          data [0 1 0 1, 0 0 0 0]
          R 2
          C 4]
      (is (top-cell-bomb? data 5 R C))
      (is (not (top-cell-bomb? data 6 R C)))
      (is (top-cell-bomb? data 7 R C))))
  )

(deftest bottom-cell-bomb-test
  (testing "is the cell in the bottom cell a bomb - bottom row"
    (let [
          data [1 1 1 1 , 1 1 1 1]
          R 2
          C 4]
      (is (not (bottom-cell-bomb? data 5 R C)))
      (is (not (bottom-cell-bomb? data 7 R C)))))
  (testing "is the cell in the bottom cell a bomb - other rows"
    (let [
          data [0 0 0 0, 0 0 0 0, 0 1 0 1]
          R 3
          C 4]
      (is (bottom-cell-bomb? data 5 R C))
      (is (not (bottom-cell-bomb? data 6 R C)))
      (is (bottom-cell-bomb? data 7 R C))))
  )

; 3 1 2 2 3
; 2 2 2 3 1
; 1 2 1 2 2
; 2 1 2 2 2
(deftest next-state-test
  (testing "compute the next state of a cell"
  (let [
        data [3 1 2 2 3, 2 2 2 3 1, 1 2 1 2 2, 2 1 2 2 2 ]
        R 4
        C 5]
    (is (= 0 (next-state 0 data R C)))  ; bomb goes off in next cell
    (is (= 0 (next-state 1 data R C)))  ; bomb goes off in this cell
    (is (= 0 (next-state 2 data R C)))  ; bomb goes off in previous cell
    (is (= 2 (next-state 3 data R C)))  ; no-op
    (is (= 0 (next-state 4 data R C)))  ; bomb goes off in the cell below

    (is (= 0 (next-state 5 data R C)))  ; bomb goes off in the cell below
    (is (= 0 (next-state 6 data R C)))  ; bomb goes off in the cell above
    (is (= 0 (next-state 7 data R C)))  ; bomb goes off in the cell below
    (is (= 0 (next-state 8 data R C)))  ; bomb goes off in next cell
    (is (= 0 (next-state 8 data R C)))  ; bomb goes off in this cell

    (is (= 0 (next-state 10 data R C)))  ; bomb goes off in this cell
    (is (= 0 (next-state 11 data R C)))  ; bomb goes off in three cells around
    (is (= 0 (next-state 12 data R C)))  ; bomb goes off in this cell
    (is (= 0 (next-state 13 data R C)))  ; bomb goes off in previous cell
    (is (= 0 (next-state 14 data R C)))  ; bomb goes off in cell above

    (is (= 0 (next-state 15 data R C)))  ; bomb goes off in cell above
    (is (= 0 (next-state 16 data R C)))  ; bomb goes off in this cell
    (is (= 0 (next-state 17 data R C)))  ; bomb goes off in previous cell
    (is (= 2 (next-state 18 data R C)))  ; no op
    (is (= 2 (next-state 19 data R C)))  ; no op
    ))
  )

; 3 1 2 2 3
; 2 2 2 3 1
; 1 2 1 2 2
; 2 1 2 2 2
(deftest boom-test
  (testing "compute the next state of a cell"
    (let [
          data     [3 1 2 2 3, 2 2 2 3 1, 1 2 1 2 2, 2 1 2 2 2 ]
          expected [0 0 0 2 0, 0 0 0 0 0, 0 0 0 0 0, 0 0 0 2 2 ]
          R 4
          C 5]
      (is (= expected (boom data R C)))))
  )