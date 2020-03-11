(ns store.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]
            [store.model :as model]))
(defn open []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete []
  (d/delete-database db-uri))

(defn create-store [name email cnpj]
  {:store/name  name
   :store/email email
   :store/cnpj  cnpj})

(def schema [{:db/ident       :store/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "nome da loja"}
             {:db/ident       :store/email
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "email da loja"}
             {:db/ident       :store/cnpj
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "cnpj da loja"}])

(defn schema-create [connection]
  (d/transact connection schema))

(defn all-stores []
  (let [conn (db/open)]
    (let [db (d/db conn)]
      (d/q '[:find ?name ?email ?cnpj
             :where [?e :store/name ?name]
             [?e :store/email ?email]
             [?e :store/cnpj ?cnpj]] db))))

(defn todas-as-lojas [db]
  (d/q '[:find ?entidade
         :where [?entidade :store/name]] db))

(defn todas-as-lojas-por-nome [db name]
  (d/q '[:find ?entidade
         :in $ ?name-a-ser-buscado
         :where [?entidade :store/name ?name-a-ser-buscado]]
       db name))