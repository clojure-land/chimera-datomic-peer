(ns arachne.chimera-datomic-peer-test
  (:require [clojure.test :refer :all]
            [clojure.spec :as s]
            [arachne.core :as core]
            [arachne.core.runtime :as rt]
            [arachne.core.config :as cfg]
            [arachne.core.config.model :as m]
            [arachne.core.dsl :as a]
            [arachne.core.config :as core-cfg]
            [arachne.chimera :as chimera]
            [arachne.chimera.dsl :as ch]
            [arachne.chimera.adapter :as ca]
            [arachne.chimera.operation :as op]
            [arachne.chimera.test-harness :as harness]
            [com.stuartsierra.component :as component]
            [arachne.chimera-datomic-peer.dsl :as dsl])
  (:import [arachne ArachneException]
           (java.util UUID Date)))

(defn test-adapter
  "Given a migration ref, return a ne"
  [migration]
  (let [uri (str "datomic:mem://" (UUID/randomUUID))]
    (println "Using test database:" uri)
    (dsl/adapter uri migration)))

(deftest test-harness
  (harness/exercise-all test-adapter [:org.arachne-framework/chimera-datomic-peer]))

(comment

  (harness/exercise-all test-adapter)


  )