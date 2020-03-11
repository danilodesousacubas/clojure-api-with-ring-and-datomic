(ns store.model
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [store.db :as db]))