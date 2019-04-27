(ns bmi-calculator.core-test
  (:require [clojure.test :refer :all]
            [bmi-calculator.core :refer :all]))

(deftest bmi-test
  (testing "Testing range and spec"
    (are [expected weight height]
        (= expected (bmi {:weight weight :height height}))
      0.01  100.0 100.0
      1.0   100.0  10.0
      )))

(deftest results-test
  (testing "Testing expected strings"
    (are [expected weight height]
        (.contains (with-out-str (print-result {:weight weight :height height})) expected)
      "underweight"       45.0   1.63
      "normal"            73.0   1.75
      "not possible"      273.0  1.73
      "overweight"        73.0   1.68
      "dangerously"       35.0   1.65
      "obese"             89.0   1.65
      "extremely"         120.0  1.65
      "not likely"        0.0    1.73)))
