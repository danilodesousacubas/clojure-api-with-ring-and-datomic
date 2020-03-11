(ns store.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]
            [store.model :as model]))

(def conn (db/open))
(def db (d/db conn))
;(d/transact conn db/schema)

(model/all-stores)

(pprint (d/q '[:find ?name ?email ?cnpj
               :where [?e :store/name ?name]
                      [?e :store/email ?email]
                      [?e :store/cnpj ?cnpj]] db))


(println (d/q '[:find ?entidade
                :where [?entidade :store/name]] db))