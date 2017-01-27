;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for equalize.clj
;	lein test clojure-learn.equalize-test
;
;To run only a specific test
;   lein test :only clojure-learn.equalize-test/foo
;

(ns clojure-learn.equalize-test
  (:require [clojure.test :refer :all]
            [clojure-learn.equalize :refer :all]))

(deftest highest-test
	(testing "determine the highest frequency from the hashmap"
		(is (= 3 (highest-frequency {3 3 2 1 1 1})))
		(is (= 4 (highest-frequency {10 4 20 3})))  
		(is (= 3 (highest-frequency {10 3 20 3 30 3 40 3})))))

(deftest create-test
	(testing "create a hashmap from a list of numbers"
		(is (= {10 1 20 2 30 3} (create-hashmap '(20 30 20 30 10 30))))
		))

(deftest delete-test
	(testing "min items to delete so that the remainder have same value"
		(is (= 2 (items-to-delete '(3 3 2 1 3))))
		(is (= 3 (items-to-delete '(2 3 2 3 1 3 3))))))
; 33 2 1 3