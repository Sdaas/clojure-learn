;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for sock.clj
;	lein test clojure-learn.sock-test
;
;To run only a specific test
;   lein test :only clojure-learn.sock-test/foo
;

(ns clojure-learn.sock-test
  (:require [clojure.test :refer :all]
            [clojure-learn.sock :refer :all]))

(deftest update-test
	(testing "empty map"
		(is (= {10 1} (update-counts {} 10))))
	(testing "add new type of sock"
		(is (= {20 1 10 2} (update-counts {10 2} 20))))
	(testing "add existing type of sock"
		(is (= {20 4 10 5} (update-counts {10 5 20 3} 20)))))

(deftest create-test
	(testing "create the map that has the count of socks"
		(is (= {10 2 20 3 30 1} (create-map [10 20 10 20 20 30])))
		(is (= {10 1 20 4} (create-map [10 20 20 20 20])))))

(deftest count-test
	(testing "count the number of possible pairs from th hashset"
		(is (= 0 (count-pairs {10 1 20 1 30 1})))
		(is (= 1 (count-pairs {10 1 20 2 30 1})))
		(is (= 2 (count-pairs {10 2 20 2 30 1})))
		(is (= 2 (count-pairs {10 1 20 4 30 1})))
		(is (= 5 (count-pairs {10 7 20 2 30 3}))))

	)
(deftest pair-test
	(testing "how many pairs of sock can be sold"
		(is (= 3 (pairs [10 20 20 10 10 30 50 10 20])))
		))


