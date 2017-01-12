;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for diagnoal.clj
;	lein test clojure-learn.diagonal-test
;
;To run only a specific test
;   lein test :only clojure-learn.diagonal-test/fubar-test
;

(ns clojure-learn.diagonal-test
  (:require [clojure.test :refer :all]
            [clojure-learn.diagonal :refer :all]))


;size size_zero
;size one
;odd
;even

(deftest size-zero
  (testing "0x0 matrix"
  	(let [ 
  		m (list )
  		]
  		(is (= 0 (solve m))))))

(deftest size-one
  (testing "1x1 matrix"
  	(let [ 
  		m (list (list 5))
  		]
  		(is (= 0 (solve m))))))

(deftest size-two
  (testing "identity matrix"
  	(let [ 
  		m (list (list 1 0) (list 0 1))
  		]
  		(is (= 2 (solve m)))))
	(testing "matrix 2"
  	(let [ 
  		m (list (list 1 10) (list 3 4))
  		]
  		(is (= 8 (solve m))))))

(deftest size-three
  (testing "3x3 matrix"
  	(let [ 
  		m (list  (list 11 2 4) (list 4 5 6) (list 10 8 -12))
  		]
  		(is (= 15 (solve m))))))


  