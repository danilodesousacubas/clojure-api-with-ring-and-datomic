(ns store.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]))

(def conn (db/open))
(pprint conn)