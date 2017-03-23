(ns arachne.chimera-datomic-peer.dsl
  "DSL code to to create a Datomic Peer Chimera Adapter"
  (:require [clojure.spec :as s]
            [arachne.error :as e :refer [deferror error]]
            [arachne.core.dsl :as core]
            [arachne.core.config :as cfg]
            [arachne.core.config.script :as script :refer [defdsl]]
            [arachne.core.util :as u]
            [arachne.chimera.specs]))

(defdsl adapter
  "Define a Datomic Peer adapter, using the specified Datomic URI and migrations"
  (s/cat :uri string?
         :migrations (s/+ :chimera.migration/name))
  [uri & migrations]
  (let [tid (cfg/tempid)]
    (script/transact
      [{:db/id tid
        :chimera.datomic-peer-adapter/uri (:uri &args)
        :chimera.adapter/migrations (map (fn [mig-name]
                                           {:chimera.migration/name mig-name})
                                      (:migrations &args))}]
      tid)))