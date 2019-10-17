; Given a BTC address, find all the outgoing transactions from that address.
; Count the number of BTCs going into those output address, and sort them by the total number of BTCs.
; Output top 10 with the BTC amount in satoshi, as a JSON array.

; Note that, you don't have to worry about any of the following:
;     Time fluctuation: A transaction happens while the test is running
;     Calculate everything in satoshi, to avoid floating point errors.
;     Each test case will query at most 10 addresses.
;     Non-standard transactions: Multi-sig and other weird cases. Only P2PKH (starting with 1) and P2SH (starting with 3) addresses will be tested.
;     Only consider transactions that has a single input of the querying address.
;     Each address will have at most 100 transactions.

(ns bitcoin-trackdown.core
  (:gen-class)
  (:require [clj-http.client :as client]
            [config.core :refer [env]]
            [cheshire.core :refer :all]
            [clojure.edn :as edn]))

(def test-addr "3NK9pxgHedLTsrgq1Wkwvx61h9hPt8VrNP")
(def apikey (:apikey env))

; TODO: Ensure output is properly decoded
;       Determine what format to return, likely not an ArrayMap
(defn get-txouts
  "Get up to 100 txouts for a BTC address."
  [addr]
  (flatten
   (map :txouts
        (:payload
         (decode (:body
          (client/get
           (str "https://api.cryptoapis.io/v1/bc/btc/mainnet/address/"
                addr "/transactions?")
           {:query-params {"index" "0"
                           "limit" "100"}
            ; :as :json
            :headers {"x-api-key" apikey}})))))))

(defn test-get
  [addr]
  (:body
   (client/get
    (str "https://api.cryptoapis.io/v1/bc/btc/mainnet/address/"
         addr "/transactions?")
    {:query-params {"index" "0"
                    "limit" "100"}
            ; :as :json
     :headers {"x-api-key" apikey}})))

; TODO: Need to agg the amounts per address

(defn get-id-amount
  "Create a map of amount and address."
  [txout]
  (let [addr (first (:addresses txout))
        amt (:amount txout)]
    (hash-map addr (edn/read-string amt))))

(defn agg-amounts
  "Aggregate all amounts by address."
  [tx]
  "")

; (defn sort-by-amount
;   "Sort the addresses by amount."
;   [xs]
;   (reverse (sort-by xs)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
