(ns store.model
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(defn create-store [name email cnpj]
  {:store/name name
   :store/email email
   :store/cnpj  cnpj})