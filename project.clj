(defproject haikunator "0.1.0"
  :description "Generate Heroku-like random names to use in your clojure applications."
  :url "https://github.com/atrox/haikunatorclj"
  :license {:name "MIT" :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :repl-options {:init-ns haikunator}

  :profiles {:dev {:plugins [[lein-cloverage "1.1.2"]
                             [lein-kibit "0.1.8"]
                             [lein-cljfmt "0.6.7"]
                             [jonase/eastwood "0.3.10"]]}})
