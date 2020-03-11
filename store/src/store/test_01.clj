(ns store.test-01
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]))

(db/delete)
(def conn (db/open))
(db/schema-create conn)

;(let [store1 (db/create-store "a1" "a2" "a3")
;      store2 (db/create-store "b1" "b2" "b3")]
;  (d/transact conn [store1,store2]))

(def db (d/db conn))

(db/todas-as-lojas db)
(db/todas-as-lojas-por-nome db "a1")

(let [store1 (db/create-store "loja1" "analia@gmail.com" "12344")
      result @(d/transact conn [store1])
      id (-> result :tempids vals first)]
  (pprint id)
  (d/transact conn [[:db/add id :store/name "loja1Centro" :store/email "loja1Centro@gmail"]])
  (d/transact conn [[:db/retract id :store/email "loja1Centro@gmail"]]))

;(pprint @(d/transact conn [[:db/add id-entidade :produto/preco 0.1M]]))
;(pprint @(d/transact conn [[:db/retract id-entidade :produto/slug "/cel

(def db (d/db conn))
;(pprint (db/todas-as-lojas-por-nome db " loja1 "))
(pprint (db/todas-as-lojas db))


(defn all-stores []
  (d/q '[:find ?name ?email ?cnpj
         :where [?e :store/name ?name]
         [?e :store/email ?email]
         [?e :store/cnpj ?cnpj]] db)
      )
(all-stores)

(db/delete)
(def conn (db/open))
(db/schema-create conn)

(let [store1 (db/create-store "loja1" "analia@gmail.com" "12344")
      result @(d/transact conn [store1])
      id (-> result :tempids vals first)]
  (pprint id)
  (d/transact conn [[:db/add id :store/name "loja1Centro"]])
  )

(def conn (db/open))
(def db (d/db conn))


(d/transact conn [[:db/add 17592186045421 :store/name "BLA"]])
(d/transact conn [[:db/retract 17592186045421 :store/name "BLA"]] )

(def db (d/db conn))
(pprint (db/todas-as-lojas-por-nome db "loja1Centro"))

(pprint (d/q '[:find ?name ?email ?cnpj
               :where [?e :store/name ?name]
               [?e :store/email ?email]
               [?e :store/cnpj ?cnpj]] db))