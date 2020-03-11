(ns store.test-02
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]))

(def conn (db/open))
(def db (d/db conn))
(db/schema-create conn)

(let [store1 (db/create-store "a1" "a2" "a3")
      store2 (db/create-store "b1" "b2" "b3")]
  (d/transact conn [store1,store2]))

(db/all-stores)
(pprint (db/todas-as-lojas-por-nome db "b1"))
(pprint (db/tall-store-entity db))