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

(deftest permutation-test
  (testing "permutation test"
    (is (= {:valid true :perm '(2 1)} (first-permutation 2 1)))
    (is (= {:valid true :perm '(1 2 3)} (first-permutation 3 0)))
    (is (= {:valid false } (first-permutation 3 1)))
    (is (= {:valid false } (first-permutation 3 2)))
    ))

(deftest foo-test
  (testing "test all the csub-cases for n=3 k=1"
    (is (= {:valid false } (first-permutation 3 1)))
    (is (= '() (solve #{1 2 3} 1 1)))
    (is (= '() (solve #{1 3} 2 1)))
    (is (= '() (solve #{3 1} 2 1)))  ; this is the same case as before
    (is (= '() (solve #{3} 3 1)))
    (is (= '() (solve #{1} 3 1)))
    ))

; data = 1 2 3
; k = 1

; d = 2  (1 3)  (3 1)
; i = 1   2 3    2 3
;     1   1 0    1 2
;     Y   Y N    Y N


; 92 14
; 98 7
; 90 15