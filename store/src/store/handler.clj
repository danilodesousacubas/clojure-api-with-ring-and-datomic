(ns store.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [store.model :as model]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(defroutes all-routes
  (GET "/" [] "Hello World")
  (GET "/stores" [] (model/m))
  (route/not-found "Not Found"))

(def app
  (-> all-routes
      wrap-json-response
      wrap-json-body))
