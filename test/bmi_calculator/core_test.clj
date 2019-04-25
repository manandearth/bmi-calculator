(ns bmi-calculator.core-test
  (:require [clojure.test :refer :all]
            [bmi-calculator.core :refer :all]))

(deftest a-test
  (testing "bmi"
    (is (=  (bmi 100 100)))))
