(defproject store "0.1.0-SNAPSHOT"
  :description ""
  :url ""
  :min-lein-version "2.0.0"
  :repositories {"my.datomic.com" {:url   "https://my.datomic.com/repo"
                                   :creds :gpg}}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-defaults "0.3.2"]
                 [com.datomic/datomic-pro "0.9.6045"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler store.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}}
  :repl-options {:init-ns store.core })
