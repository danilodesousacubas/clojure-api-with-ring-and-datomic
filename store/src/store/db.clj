(ns store.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/store")

(defn open []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete []
  (d/delete-database db-uri))