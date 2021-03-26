(ns kafka.admin
  (:require [jackdaw.client :as j.cli]
            [jackdaw.admin :as j.admin]
            [jackdaw.serdes.json :as json.serdes]
            [config :as cfg])
  (:import (org.apache.kafka.common.serialization Serdes)))

(def default-serdes
  {:key-serde   (Serdes/String)
   :value-serde (json.serdes/serde)})

(def default-config
  {"bootstrap.servers" "localhost:9092"})

(defn admin-client [& config]
  (j.admin/->AdminClient
    (merge default-config config)))

;(defn create-topic
;  [admin-client {:keys [topic-name
;                        parition-count
;                        replication-factor]}])

(def client (j.admin/->AdminClient default-config))
(defn create-topic [topic-name]
  (j.admin/create-topics! client
                          [{:topic-name         topic-name
                            :partition-count    3
                            :replication-factor 1}]))

;(def producer-config
;  (merge default-config
;         default-serdes))

(defn send-message [topic-name key value]
  (with-open [my-producer (j.cli/producer default-config default-serdes)]
    @(j.cli/produce! my-producer {:topic-name topic-name} key value)))
#_(create-topic "topico")
#_(send-message "participants-response" "msg" "{\"nome\":1}\"")
