;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for repeated.clj
;	lein test clojure-learn.repeated-test
;
;To run only a specific test
;   lein test :only clojure-learn.repeated-test/foo
;

(ns clojure-learn.repeated-test
  (:require [clojure.test :refer :all]
            [clojure-learn.repeated :refer :all]))

(deftest occurences-test
	(testing "Number of occurences of a letter in a string"
		(is (= 0 (occurences "hello" \a)))
		(is (= 6 (occurences "aaaaaa" \a)))
		(is (= 2 (occurences "papal" \a)))
	))

(deftest occurences-n-test
	(testing "zero occurrences"
		(is (= 0 (occurences-n "hello" 0 \a)))
		(is (= 0 (occurences-n "aaaaaa" 0 \a)))
		(is (= 0 (occurences-n "hello" 3 \a)))
		(is (= 0 (occurences-n "hello" 12 \a))))
	(testing "just the letter a"
		(is (= 1 (occurences-n "a" 1 \a)))
		(is (= 10 (occurences-n "aaa" 10 \a)))
		(is (= 3765 (occurences-n "aaaaa" 3765 \a))))
	(testing "words"
		(is (= 0 (occurences-n "baar" 1 \a)))
		(is (= 2 (occurences-n "baar" 3 \a)))
		(is (= 21 (occurences-n "baar" 42 \a)))))