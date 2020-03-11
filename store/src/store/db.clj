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

(defn todas-as-lojas [db]
  (d/q '[:find ?entidade
         :where [?entidade :store/name]] db))

(defn todas-as-lojas-por-nome [db name]
  (d/q '[:find ?entidade
         :in $ ?name-a-ser-buscado
         :where [?entidade :store/name ?name-a-ser-buscado]]
       db name))

;(let [store1 (db/create-store "a1" "a2" "a3")
;     store2 (db/create-store "b1" "b2" "b3")]
; (d/transact conn [store1, store2]))


;(let [store1 (create-store "hut1" "hut1" "hut1")
;      resultado @(d/transact connection [store1])]
;  (pprint resultado))



