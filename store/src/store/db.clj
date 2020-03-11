(ns store.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]
            [store.model :as model]))

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

(def db-uri "datomic:dev://localhost:4334/store")
(def connection (db/open))
(def bd (d/db connection))

(pprint bd)
(d/transact connection db/schema)

(let [store1 (create-store "hut1" "hut1" "hut1")
      store2 (create-store "hut2" "hut2" "hut2")]
  (d/transact connection [store1 store2]))


;(d/q '[:find ?entidade :where [?store/name]] db)
;  ;(pprint (d/q '[:find ?en : where []]))

(defn get-store []
    (d/q '[:find ?entidade
           :where [?entidade :store/name]] bd))
;
;
;(def bd (d/db connection))

(pprint (d/q '[:find ?en :where [?en :store/name]  ] bd))




