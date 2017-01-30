;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for distance.clj
;	lein test clojure-learn.distance-test
;
;To run only a specific test
;   lein test :only clojure-learn.distance-test/foo
;

(ns clojure-learn.distance-test
  (:require [clojure.test :refer :all]
            [clojure-learn.distance :refer :all]))

(deftest delta-test
	(testing "difference between two items in a sorted list"
		(is (= [] (delta [])))
		(is (= [] (delta [10])))
		(is (= [2] (delta [7 9])))
		(is (= [2 1 5 10] (delta [2 4 5 10 20])))
		(is (= [2 1] (delta [7 9 10])))))

(deftest minimum-delta--test
	(testing "The minimum diff between two elements of a list"
		(is (= 1 (minimum-delta #{4 6 3 11}))) ; 3 4 6 11 -> 1
		(is (= 2 (minimum-delta #{6 3 11 8}))) ; 3 6 8 11 -> 2
		(is (= 1 (minimum-delta [4 6 3 11]))) ; 3 4 6 11 -> 1
		(is (= 2 (minimum-delta [6 3 11 8]))) ; 3 6 8 11 -> 2
		(is (= 2 (minimum-delta [6 8 2 4]))))); 2 4 6 8  -> 2 

(deftest create-test
	(testing "create the hashmap"
		(let[
			data [10 20 10 30]
			expected { 10 #{0 2} 20 #{1} 30 #{3}}
			]
			(is (= expected (create-map data))))
		(let[
			data [10 20 30 30 40 30 10]
			expected {10 #{0 6}, 20 #{1}, 30 #{3 2 5}, 40 #{4}}
			]
			(is (= expected (create-map data))))
		))

(deftest minimum-test
	(testing "find the minimum distance"
		(is (= 2 (minimum [7 1 3 4 1 7 1]))))
	(testing "when the data doesnt contain any duplicates"
		(is (= -1 (minimum [10 20 30 40 50]))))
		)
