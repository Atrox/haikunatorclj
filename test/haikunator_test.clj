(ns haikunator-test
  (:require [clojure.test :refer :all]
            [haikunator :refer [haikunate]]))

(deftest general-functionality
  (let [matching (fn [opts regex] (re-matches regex (haikunate opts)))
        not-matching (complement matching)]
    (do
      (is (matching {} #"^[a-z]+-[a-z]+-[0-9]{4}$"))
      (is (matching {:token-hex true} #"^[a-z]+-[a-z]+-[0-f]{4}$"))
      (is (matching {:token-length 9} #"^[a-z]+-[a-z]+-[0-9]{9}$"))
      (is (matching {:token-length 9 :token-hex true} #"^[a-z]+-[a-z]+-[0-f]{9}$"))
      (is (matching {:token-length 0} #"^[a-z]+-[a-z]+$"))
      (is (matching {:delimiter "."} #"^[a-z]+\.[a-z]+\.[0-9]{4}$"))
      (is (matching {:token-length 0 :delimiter " "} #"^[a-z]+ [a-z]+$"))
      (is (matching {:token-length 0 :delimiter ""} #"^[a-z]+$"))
      (is (matching {:token-chars "xyz"} #"^[a-z]+-[a-z]+-[x-z]{4}$"))
      (is (matching {:adjectives ["adjective"] :nouns ["noun"]} #"^adjective-noun-\d{4}$"))
      (is (matching {:adjectives nil :nouns nil} #"^\d{4}$"))
      (is (matching {:adjectives nil :nouns nil :token-length 0} #"^$")))))

(deftest seed
  (is (= (haikunate {:random-seed 1001}) "wandering-heart-4018")))

(deftest without-params
  (is (re-matches #"^[a-z]+-[a-z]+-[0-9]{4}$" (haikunate))))
