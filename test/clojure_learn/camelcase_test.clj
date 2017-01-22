;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for camelcase.clj
;	lein test clojure-learn.camelcase-test
;
;To run only a specific test
;   lein test :only clojure-learn.camelcase-test/foo
;

(ns clojure-learn.camelcase-test
  (:require [clojure.test :refer :all]
            [clojure-learn.camelcase :refer :all]))

(deftest index-test
	(testing "tests for index-if-uppercase"
		(is (= `() (index-if-uppercase "")))  
		(is (= `() (index-if-uppercase "hello")))
		(is (= `(1 4) (index-if-uppercase "hEllO")))
		(is (= `(5 10 14) (index-if-uppercase "helloWorldTestAgain")))))

(deftest word-test
	(testing "test words"
		(is (= `() (words "")))
		(is (= `("hello") (words "hello")))
		(is (= `("h" "Ell" "O") (words "hEllO")))
		(is (= `("hello" "World" "Test" "Again") (words "helloWorldTestAgain")))))

(deftest zero-test
	(testing "empty string should return zero count"
		(is (= 0 (count (words ""))))))

(deftest single-test
	(testing "camel case word with just one word"
		(is (= 1 (count (words "fubar"))))))

(deftest misc-test
	(testing "camel case words with multiple words"
		(is (= 3 (count (words "fubarHelloWorld"))))
		(is (= 5 (count (words "saveChangesInTheEditor"))))
		(is (= 5 (count (words "thisIsATestMessage")))) ; Handling for a single "A" 
		(is (= 4 (count (words "aXYZ")))))) ; multiple single word capital letters

(deftest fubar
	(testing "test words"
		(is (= `() (words "")))))