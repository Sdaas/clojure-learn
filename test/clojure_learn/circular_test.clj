;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for circular.clj
;	lein test clojure-learn.circular-test
;
;To run only a specific test
;   lein test :only clojure-learn.circular-test/foo
;

(ns clojure-learn.circular-test
  (:require [clojure.test :refer :all]
            [clojure-learn.circular :refer :all]))

;nkm
(deftest solve-test
	(testing "zero rotation"
		(is (= 4 (solve 10 0 4))))  ; x = m for zero rotation
	(testing "rotate 1"
		(is (= 3 (solve 10 1 4)))
		(is (= 8 (solve 10 1 9)))
		(is (= 9 (solve 10 1 0))) 
		(is (= 0 (solve 10 1 1))))
	(testing "rotate 2"
		(is (= 0 (solve 10 2 2)))
		(is (= 3 (solve 10 2 5)))
		(is (= 7 (solve 10 2 9)))
		(is (= 8 (solve 10 2 0))) 
		(is (= 9 (solve 10 2 1))))
	(testing "rotate by n-1"
		(is (= 0 (solve 10 9 9)))
		(is (= 3 (solve 10 9 2)))
		(is (= 7 (solve 10 9 6)))
		(is (= 8 (solve 10 9 7))) 
		(is (= 9 (solve 10 9 8))))
	(testing "rotate by n"
		(is (= 4 (solve 10 10 4))))
	(testing "rotate by 2n+1"
		(is (= 3 (solve 10 21 4)))
		(is (= 8 (solve 10 21 9)))
		(is (= 9 (solve 10 21 0))) 
		(is (= 0 (solve 10 21 1)))))