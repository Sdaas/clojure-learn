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

(deftest my-min-test
	(testing "my-min"
		(is (= 10 (my-min 20 10)))
		(is (= 10 (my-min 10 20)))
		(is (= \c (my-min \s \c)))
		(is (= \c (my-min \c \s)))))

(deftest next-larger-from-list-test
	(testing "get the next largest number from list"
		(is (= 5 (next-larger-in-list 2 [7 0 9 2 6 5 8])))
		(is (nil? (next-larger-in-list 9 [7 0 9 2 6 5 8]))))
	(testing "get the next largest char from list"
		(is (= \e (next-larger-in-list \b [\g \a \i \b \f \e \h])))
		(is (nil? (next-larger-in-list \i [\g \a \i \b \f \e \h])))))


(deftest remove-from-list-test
	(testing "simple case"
		(is (= '(3 1 2 5 4) (remove-from-list 7 '(3 1 2 7 5 4))))
		(is (= '(\a \b \d) (remove-from-list \c '(\a \b \c \d)))))
	(testing "with duplicate entries"
		(is (= '(3 2 1) (remove-from-list 1 '(3 1 2 1))))
		(is (= '(3 1 2) (remove-from-list 3 '(3 3 1 2))))
		(is (= '(3 1 2) (remove-from-list 2 '(3 1 2 2))))
		(is (= '(\a \b \d) (remove-from-list \b '(\a \b \b \d)))))
	(testing "no matches"
		(is (= '(3 1 2 5 4) (remove-from-list 7 '(3 1 2 5 4))))
		(is (= '(\a \b \d) (remove-from-list \c '(\a \b \d))))))

(deftest next-largest-test
	(testing "next largest number using the n-1 significant digits"
		(is (= '(1 2 4 3) (next-largest '(1 2 3 4))))
		(is (= '(1 3 2 4) (next-largest '(1 2 4 3))))
		(is (= '(3 2 1 4) (next-largest '(3 1 4 2))))
		(is (= '(3 2 4 1) (next-largest '(3 2 1 4))))
		(is (= '(3 4 1 2) (next-largest '(3 2 4 1))))
		(is (= '(3 4 2 1) (next-largest '(3 4 1 2))))
		(is (= '(4 1 2 3) (next-largest '(3 4 2 1)))))
	(testing "next largest number using the n-1 significant digits"
		(is (nil? (next-largest '(4 3 2 1)))))
	(testing "with strings"
		(is (= '(\b \a) (next-largest '(\a \b))))
		(is (nil? (next-largest '(\b \b))))
		(is (= '(\h \e \g \f) (next-largest '(\h \e \f \g))))
		(is (= '(\d \h \k \c) (next-largest '(\d \h \c \k))))
		(is (= '(\h \c \d \k) (next-largest '(\d \k \h \c))))))

(deftest sample-tests
	(testing "test case with the sample input"
		(is (= "jkscckttaeifiksgkxmx" (process "jkscckttaeifiksgkmxx")))
		(is (= "acbc" (process "abcc")))
		))

;5
;ab
;bb
;hefg
;dhck
;dkhc