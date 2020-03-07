(ns store.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]
            [store.model :as model]))

;(def conn (db/open))
;(def db (d/db conn))
;(d/transact conn db/schema)

(model/all-stores)


