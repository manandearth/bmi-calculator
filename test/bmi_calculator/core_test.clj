(ns bmi-calculator.core-test
  (:require [clojure.test :refer :all]
            [bmi-calculator.core :refer :all]))

(deftest bmi-test
  (testing "Testing range and spec"
    (are [expected arg-map]
        (= expected (bmi (:x arg-map) (:y arg-map)))
      0.01 {:x 100.0 :y 100.0}
      1.0 {:x 100.0 :y 10.0}
      )))

(defn get-input-helper
  "replacing the user stdin string"
  []
  (str 180.0))

(prompt-height get-input-helper 77.0)
(Double/parseDouble (get-input-helper))

(deftest input-test
  (print-result 73.0 170.0)
  (is (= nil (prompt-height get-input-helper 77.0) )))


(deftest results-test
  (testing "Testing expected strings"
    (are [expected arg-map]
        (.contains (vari-result str (:weight arg-map) (:height arg-map)) expected)
      "underweight"      {:weight 45.0 :height 1.63}
      "normal"           {:weight 73.0 :height 1.75}
      "not possible"     {:weight 273.0 :height 1.73}
      "overweight"       {:weight 73.0 :height 1.68}
      "dangerously"      {:weight 35.0 :height 1.65}
      "obese"            {:weight 89.0 :height 1.65}
      "extremely"        {:weight 120.0 :height 1.65}
      "not likely"       {:weight 0.0 :height 1.73})))
