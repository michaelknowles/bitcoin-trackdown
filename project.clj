(defproject bitcoin-trackdown "0.1.0-SNAPSHOT"
  :description "Real.dev exercise Bitcoin Trackdown"
  :url "https://real.dev/task/bitcoin-trackdown"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [clj-http "3.10.0"]
                 [cheshire "5.9.0"]
                 [yogthos/config "1.1.6"]]
  :main ^:skip-aot bitcoin-trackdown.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
