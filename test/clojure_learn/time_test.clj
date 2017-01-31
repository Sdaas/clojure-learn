;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for bonappetit.clj
;	lein test clojure-learn.time-test
;
;To run only a specific test
;   lein test :only clojure-learn.time-test/foo
;

(ns clojure-learn.time-test
  (:require [clojure.test :refer :all]
            [clojure-learn.time :refer :all]))

(deftest minutes-test
	(testing "convert minutes to words < 30"
		(is (= "one minute" (minutes-to-word 1)))
		(is (= "two minutes" (minutes-to-word 2)))
		(is (= "twenty one minutes" (minutes-to-word 21)))
		(is (= "nineteen minutes" (minutes-to-word 19))))
	(testing "convert minutes to words > 30"
		(is (= "one minute" (minutes-to-word 59)))
		(is (= "two minutes" (minutes-to-word 58)))
		(is (= "twenty one minutes" (minutes-to-word 39)))
		(is (= "nineteen minutes" (minutes-to-word 41)))))

(deftest hours-test
	(testing "convert hours to words"
		(is (= "one" (hours-to-word 1)))
		(is (= "two" (hours-to-word 2)))
		(is (= "eight" (hours-to-word 8)))
		(is (= "twelve" (hours-to-word 12)))))

(deftest word-test
	(testing "the time in words"
		(is (= "five o' clock" (to-words "5:00")))
		(is (= "one minute past five" (to-words "5:01")))
		(is (= "ten minutes past five" (to-words "5:10")))
		(is (= "twenty eight minutes past five" (to-words "5:28")))
		(is (= "half past five" (to-words "5:30")))
		(is (= "twenty minutes to six" (to-words "5:40")))
		(is (= "thirteen minutes to six" (to-words "5:47")))
		(is (= "one minute to six" (to-words "5:59")))
		(is (= "ten minutes past twelve" (to-words "12:10")))
		(is (= "ten minutes to one" (to-words "12:50")))
		(is (= "quarter past three" (to-words "3:15")))
		(is (= "quarter to four" (to-words "3:45")))
		(is (= "quarter to one" (to-words "12:45")))
		))

(deftest word2-test
	(testing "the time in words"
		(is (= "five o' clock" (to-words 5 0)))
		(is (= "one minute past five" (to-words 5 01)))
		(is (= "ten minutes past five" (to-words 5 10)))
		(is (= "twenty eight minutes past five" (to-words 5 28)))
		(is (= "half past five" (to-words 5 30)))
		(is (= "twenty minutes to six" (to-words 5 40)))
		(is (= "thirteen minutes to six" (to-words 5 47)))
		(is (= "one minute to six" (to-words 5 59)))
		(is (= "ten minutes past twelve" (to-words 12 10)))
		(is (= "ten minutes to one" (to-words 12 50)))
		))

