(ns store.model
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]))

(defn all-stores []
  (let [conn (db/open)]
    (let [db (d/db conn)]
      (d/q '[:find ?name ?email ?cnpj
             :where [?e :store/name ?name]
                    [?e :store/email ?email]
                    [?e :store/cnpj ?cnpj]] db))))