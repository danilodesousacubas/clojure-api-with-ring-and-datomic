(ns store.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/store")

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



