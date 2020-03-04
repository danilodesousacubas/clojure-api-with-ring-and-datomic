(ns store.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]
            [store.model :as model]))

(def conn (db/open))
(pprint conn)

(d/transact conn db/schema)

(let [store (model/create-store "store1", "store@gmail" "1234")
      store (model/create-store "store2", "store2@gmail" "12345")]
  (d/transact conn [store]))
