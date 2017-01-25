(ns clojure-learn.core
	(:require [clojure-learn.pdfviewer :refer :all])
	(use [clojure.string :only (split triml)])
 	(:gen-class))


(defn -main
  [& args]
  (clojure-learn.modfib/process))
