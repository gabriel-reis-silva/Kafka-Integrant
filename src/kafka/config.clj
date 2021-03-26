(ns config
  (:require [jackdaw.client :as j.cli]
            [jackdaw.admin :as j.admin]
            [jackdaw.serdes.json :as json.serdes])
(:import (org.apache.kafka.common.serialization Serdes)))

(defn set-default-serdes[k v]
  "This function set the default serdes that u need"
  {:key-serde k
   :value-serde v})

(set-default-serdes 
 (Serdes/String )
 (json.serdes/serde))

(defn set-default-config [m]
  "this function set the default config by a map as an arg"
  :config m)

(set-default-config   {"bootstrap.servers" "localhost:9092"})
