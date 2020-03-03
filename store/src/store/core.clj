(ns store.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]))

(pprint (db/open))
(def conn (db/open))