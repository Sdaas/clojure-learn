;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for encryption.clj
;	lein test clojure-learn.encryption-test
;
;To run only a specific test
;   lein test :only clojure-learn.encryption-test/foo
;

(ns clojure-learn.encryption-test
  (:require [clojure.test :refer :all]
            [clojure-learn.encryption :refer :all]))

(deftest remove-test
	(testing "remove spaces from a string"
		(is (= '() (remove-spaces "")))
		(is (= '() (remove-spaces "     ")))
		(is (= '(\h \e \l \l \o) (remove-spaces "hello")))
		(is (= '(\h \e \l \l \o) (remove-spaces "h e l l o")))
		(is (= '(\h \e \l \l \o) (remove-spaces "h  e  l  l       o")))
	))

(deftest grid-test
	(testing "convert a sequence into a grid"
		(is (=  ['(\a \b) '(\c)] (grid '(\a \b \c))))
		(is (= ['(\h \a \v \e) '(\a \n \i \c) '(\e \d \a \y)] (grid '(\h \a \v \e \a \n \i \c \e \d \a \y))))
		(is (= ['(\f \e \e \d) '(\t \h \e \d) '(\o \g)] (grid '(\f \e \e \d \t \h \e \d \o \g))))
	))

(deftest scan-test
	(testing "scan the grid vertically"
		(is (= ['(\h \a \e) '(\a \n \d) '(\v \i \a) '(\e \c \y)] (scan ['(\h \a \v \e) '(\a \n \i \c) '(\e \d \a \y)])))
		(is (= ['(\f \t \o) '(\e \h \g) '(\e \e) '(\d \d)] (scan ['(\f \e \e \d) '(\t \h \e \d) '(\o \g)] )))
		(is (= ['(\a \d \g) '(\b \e ) '(\c \f)] (scan ['(\a \b \c) '(\d \e \f) '(\g)] )))
	))

(deftest string-test
	(testing "converting the scanned grid to a string"
		(is ( = "hae and via ecy" (tostring ['(\h \a \e) '(\a \n \d) '(\v \i \a) '(\e \c \y)])))
		(is ( = "fto ehg ee dd" (tostring ['(\f \t \o) '(\e \h \g) '(\e \e) '(\d \d)] )))
	))
; The message "have a nice day"
; becomes the string "haveaniceday"
; In grid form, this is
;
; have
; anic
; eday
; 
; The output is "hae and via ecy"