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

(def conn (db/open))
(def db (d/db conn))

(defn get-store []
  (d/q '[:find ?entidade
         :where [?entidade :store/name]] db))

;(defn m []
;  (let [r (db/create-store "store1" "a" "s")]))

(get-store)



(defn get-store []
  (d/q '[:find ?entidade
         :where [?entidade :store/name]] db))