;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for bigint.clj
;	lein test clojure-learn.bigint-test
;
;To run only a specific test
;   lein test :only clojure-learn.bigint-test/string2bigint-test
;

(ns clojure-learn.bigint-test
  (:require [clojure.test :refer :all]
            [clojure-learn.bigint :refer :all]))

(deftest foo
  (testing "Foo"
    (testing "converting 0" 
    	(is (= 0 (compare [0] (string2bigint "0"))) "Should return [0]"))))

(deftest string2bigint-test
  (testing "string2bigint tests"
    (testing "converting 0" 
    	(is (= 0 (compare [0] (string2bigint "0"))) "Should return [0]"))
    (testing "postive integers"
		(is (= 0 (compare [1] (string2bigint "1"))) "Should return [1]")
		(is (= 0 (compare [0 1] (string2bigint "10"))) "Should return [0 1]")
		(is (= 0 (compare [0 0 4] (string2bigint "400"))) "Should return [0 0 4]")
		(is (= 0 (compare [2 6 5 1] (string2bigint "1562"))) "Should return [2 6 5 1]")
		(is (= 0 (compare [7 2 1 9 3 2 1 5 0 4 0 2] (string2bigint "204051239127"))) "Should return [7 2 .. 0 2]"))
    (testing "negative integers")))
