(ns store.load-data
  (:require [store.db :as db]
            [store.model :as model]
            [datomic.api :as d]))

(let [conn (db/open)]
  (let [store1 (db/create-store "store1", "store@gmail.com", "1234")
        store2 (db/create-store "store2", "store2@gmail.com", "12345")
        store3 (db/create-store "store2", "store3@gmail.com.br", "12345")]
    (d/transact conn [store1, store2, store3])))