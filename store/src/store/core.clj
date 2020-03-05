(ns store.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]
            [store.model :as model]))

(def conn (db/open))
(def db (d/db conn))
(d/transact conn db/schema)
(pprint conn)

(let [store (model/create-store "store1", "store@gmail.com" "1234")
      store (model/create-store "store2", "store2@gmail.com" "12345")
      store (model/create-store "store2", "store3@gmail.com.br" "12345")]
  (d/transact conn [store]))

(pprint (d/q '[:find ?e
               :where [?e :store/name]] db))

(defn store-name [db store-search]
  (d/q '[:find ?e
         :where [?e :store/name "store/name"]] db))
