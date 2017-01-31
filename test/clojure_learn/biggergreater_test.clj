;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for biggergreater.clj
;	lein test clojure-learn.biggergreater-test
;
;To run only a specific test
;   lein test :only clojure-learn.biggergreater-test/foo
;

(ns clojure-learn.biggergreater-test
  (:require [clojure.test :refer :all]
            [clojure-learn.biggergreater :refer :all]))

(deftest next-larger-from-list-test
	(testing "get the next largest number from list"
		(is (= 5 (next-larger-from-list 2 [7 0 9 2 6 5 8]))))
	(testing "get the next largest number from list"
		(is (nil? (next-larger-from-list 9 [7 0 9 2 6 5 8])))))

(deftest solve2-test
	(testing "next largest number using the n-1 significant digits"
		(is (= '(4 1 2 3) (solve2 '(3 1 4 2)))))
	(testing "next largest number using the n-1 significant digits"
		(is (nil? (solve2 '(4 1 3 2))))))