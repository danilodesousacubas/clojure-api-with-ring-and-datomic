(ns store.load-data
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]
            [store.model :as model]))

(def conn (db/open))

(defn load-stores [store1 (model/create-store "store1", "store@gmail.com" "1234")
      store2 (model/create-store "store2", "store2@gmail.com" "12345")
      store3 (model/create-store "store2", "store3@gmail.com.br" "12345")]
  (d/transact conn [store1, store2, store3]))