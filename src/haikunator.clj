(ns haikunator
  (:require [clojure.string :as string])
  (:import (java.util Random)))

(def ^:private default-adjectives ["aged" "ancient" "autumn" "billowing" "bitter" "black" "blue" "bold"
                                   "broad" "broken" "calm" "cold" "cool" "crimson" "curly" "damp"
                                   "dark" "dawn" "delicate" "divine" "dry" "empty" "falling" "fancy"
                                   "flat" "floral" "fragrant" "frosty" "gentle" "green" "hidden" "holy"
                                   "icy" "jolly" "late" "lingering" "little" "lively" "long" "lucky"
                                   "misty" "morning" "muddy" "mute" "nameless" "noisy" "odd" "old"
                                   "orange" "patient" "plain" "polished" "proud" "purple" "quiet" "rapid"
                                   "raspy" "red" "restless" "rough" "round" "royal" "shiny" "shrill"
                                   "shy" "silent" "small" "snowy" "soft" "solitary" "sparkling" "spring"
                                   "square" "steep" "still" "summer" "super" "sweet" "throbbing" "tight"
                                   "tiny" "twilight" "wandering" "weathered" "white" "wild" "winter" "wispy"
                                   "withered" "yellow" "young"])

(def ^:private default-nouns ["art" "band" "bar" "base" "bird" "block" "boat" "bonus"
                              "bread" "breeze" "brook" "bush" "butterfly" "cake" "cell" "cherry"
                              "cloud" "credit" "darkness" "dawn" "dew" "disk" "dream" "dust"
                              "feather" "field" "fire" "firefly" "flower" "fog" "forest" "frog"
                              "frost" "glade" "glitter" "grass" "hall" "hat" "haze" "heart"
                              "hill" "king" "lab" "lake" "leaf" "limit" "math" "meadow"
                              "mode" "moon" "morning" "mountain" "mouse" "mud" "night" "paper"
                              "pine" "poetry" "pond" "queen" "rain" "recipe" "resonance" "rice"
                              "river" "salad" "scene" "sea" "shadow" "shape" "silence" "sky"
                              "smoke" "snow" "snowflake" "sound" "star" "sun" "sun" "sunset"
                              "surf" "term" "thunder" "tooth" "tree" "truth" "union" "unit"
                              "violet" "voice" "water" "waterfall" "wave" "wildflower" "wind" "wood"])

(defn- random-seeded
  "creates a seeded rng"
  [seed]
  (if seed
    (Random. seed)
    (Random.)))

(defn- random-coll
  "gets a random element from a coll with the specified rng"
  [^Random random coll]
  (when (not-empty coll)
    (get coll (.nextInt random (count coll)))))

(defn haikunate
  "generates heroku-like random names"
  ([{:keys [adjectives nouns random-seed delimiter token-length token-hex token-chars]
     :or   {adjectives   default-adjectives
            nouns        default-nouns
            delimiter    "-"
            token-length 4
            token-hex    false
            token-chars  "0123456789"}}]
   (let [random (random-seeded random-seed) token-chars (vec (or (and token-hex "0123456789abcdef") token-chars))]
     (string/join
      delimiter
      (remove empty?
              (vector
               (random-coll random adjectives)
               (random-coll random nouns)
               (string/join
                ""
                (map (fn [& _] (random-coll random token-chars))
                     (range token-length))))))))

  ([] (haikunate {})))
