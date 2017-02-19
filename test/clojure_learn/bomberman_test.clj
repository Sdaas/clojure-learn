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
    (let [
          data [0 1 2 3 4 5]
          exp [0 0 1 2 3 4]
          out (tick data)
          ]
      (is (vector? out))   ; the output must be a vector
      (is (= out exp))     ; the output must match expectations
      )
    )
  )


(deftest place-bombs-test
  (testing "place bombs in all places wehre ther is no bomb"
    (let [
          data [0 1 0 2 0 3 0 4]
          exp [3 1 3 2 3 3 3 4]
          out (place-bombs data)
          ]
      (is (vector? out)) ; the output of the function must be a vector
      (is (= out exp)) ; output must match expectations
      )
    )
  )


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
    (is (= 0 (next-state data 0 R C)))  ; bomb goes off in next cell
    (is (= 0 (next-state data 1 R C)))  ; bomb goes off in this cell
    (is (= 0 (next-state data 2 R C)))  ; bomb goes off in previous cell
    (is (= 2 (next-state data 3 R C)))  ; no-op
    (is (= 0 (next-state data 4 R C)))  ; bomb goes off in the cell below

    (is (= 0 (next-state data 5 R C)))  ; bomb goes off in the cell below
    (is (= 0 (next-state data 6 R C)))  ; bomb goes off in the cell above
    (is (= 0 (next-state data 7 R C)))  ; bomb goes off in the cell below
    (is (= 0 (next-state data 8 R C)))  ; bomb goes off in next cell
    (is (= 0 (next-state data 9 R C)))  ; bomb goes off in this cell

    (is (= 0 (next-state data 10 R C)))  ; bomb goes off in this cell
    (is (= 0 (next-state data 11 R C)))  ; bomb goes off in three cells around
    (is (= 0 (next-state data 12 R C)))  ; bomb goes off in this cell
    (is (= 0 (next-state data 13 R C)))  ; bomb goes off in previous cell
    (is (= 0 (next-state data 14 R C)))  ; bomb goes off in cell above

    (is (= 0 (next-state data 15 R C)))  ; bomb goes off in cell above
    (is (= 0 (next-state data 16 R C)))  ; bomb goes off in this cell
    (is (= 0 (next-state data 17 R C)))  ; bomb goes off in previous cell
    (is (= 2 (next-state data 18 R C)))  ; no op
    (is (= 2 (next-state data 19 R C)))  ; no op
    ))
  )

; 3 1 2 2 3
; 2 2 2 3 1
; 1 2 1 2 2
; 2 1 2 2 2
(deftest boom-test
  (testing "compute the next state of a cell after explosion"
    (let [
          data     [3 1 2 2 3, 2 2 2 3 1, 1 2 1 2 2, 2 1 2 2 2 ]
          expected [0 0 0 2 0, 0 0 0 0 0, 0 0 0 0 0, 0 0 0 2 2 ]
          R 4
          C 5]
      (is (= expected (boom data R C)))))
  )

(deftest simulate-test
  (testing "do a simultation over time"
    (let [
          data [0 3 0 0, 0 0 3 0, 0 0 3 0, 3 0 0 0]
          exp1 [0 2 0 0, 0 0 2 0, 0 0 2 0, 2 0 0 0] ; expected state after 1 second
          exp2 [3 1 3 3, 3 3 1 3, 3 3 1 3, 1 3 3 3] ; expected state after 2 second
          exp3 [0 0 0 2, 2 0 0 0, 0 0 0 0, 0 0 0 2] ; after 3 seconds
          exp4 [3 3 3 1, 1 3 3 3, 3 3 3 3, 3 3 3 1] ; after 4 seconds
          exp5 [0 2 0 0, 0 0 2 0, 0 2 2 0, 2 2 0 0] ; after 5 seconds
          exp6 [3 1 3 3, 3 3 1 3, 3 1 1 3, 1 1 3 3]
          exp7 [0 0 0 2, 2 0 0 0, 0 0 0 0, 0 0 0 2]
          exp8 exp4 ; should bethe same as exp4
          exp9 exp5 ; should be the same as exp5
          R 4
          C 4]
      (is (= data (simulate data R C 0)))
      (is (= exp1 (simulate data R C 1)))
      (is (= exp2 (simulate data R C 2)))
      (is (= exp3 (simulate data R C 3)))
      (is (= exp4 (simulate data R C 4)))
      (is (= exp5 (simulate data R C 5)))
      (is (= exp6 (simulate data R C 6)))
      (is (= exp7 (simulate data R C 7)))
      (is (= exp8 (simulate data R C 8)))
      (is (= exp9 (simulate data R C 9)))
      )))

;
; 0 3 0 0     0 2 0 0     3 1 3 3     0 0 0 2     3 3 3 1     0 2 0 0     3 1 3 3     0 0 0 2
; 0 0 3 0     0 0 2 0     3 3 1 3     2 0 0 0     1 3 3 3     0 0 2 0     3 3 1 3     2 0 0 0
; 0 0 3 0     0 0 2 0     3 3 1 3     0 0 0 0     3 3 3 3     0 2 2 0     3 1 1 3     0 0 0 0
; 3 0 0 0     2 0 0 0     1 3 3 3     0 0 0 2     3 3 3 1     2 2 0 0     1 1 3 3     0 0 0 2
