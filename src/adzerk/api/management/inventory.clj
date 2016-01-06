(ns adzerk.api.management.inventory
  (:require [adzerk.api :refer (defapi deflist defget)]
            [adzerk.helpers :refer (clj->csharp)]))

(defapi create-site!
  :post "/v1/site"
  [api-key req]
  (doapi api-key {:site (clj->csharp req)}))

(defapi update-site!
  :put "/v1/site/%s"
  [api-key {:keys [id] :as req}]
  (doapi api-key [id] {:site (clj->csharp req)}))

(defapi delete-site!
  :put "/v1/site/%s"
  [api-key {:keys [id] :as req}]
  (doapi api-key [id] {:site (clj->csharp (assoc req :is-deleted true))}))

;;---------------------------------------------------------------------------;;

(defapi add-site-to-channel!
  :post "/v1/channelSite"
  [api-key req]
  (doapi api-key {:channelSite (clj->csharp req)}))

(defapi update-site-channel-map!
  :put "/v1/channelSite"
  [api-key {:keys [channelid] :as req}]
  (doapi api-key {:channelSite (clj->csharp req)}))

(defapi remove-site-from-channel!
  :get "/v1/channel/%s/site/%s/delete"
  [api-key {:keys [channelid siteid] :as req}]
  (doapi api-key [channelid siteid] nil))

;;---------------------------------------------------------------------------;;

(defapi create-channel!
  :post "/v1/channel"
  [api-key req]
  (doapi api-key {:channel (clj->csharp req {:cpm 0 :engine 0})}))

(defapi update-channel!
  :put "/v1/channel/%s"
  [api-key {:keys [id] :as req}]
  (doapi api-key [id] {:channel (clj->csharp req {:cpm 0 :engine 0})}))

(defapi delete-channel!
  :get "/v1/channel/%s/delete"
  [api-key {:keys [id]}]
  (doapi api-key [id] nil))

;;---------------------------------------------------------------------------;;

(defapi create-priority!
  :post "/v1/priority"
  [api-key req]
  (doapi api-key {:priority (clj->csharp req)}))

(defapi update-priority!
  :put "/v1/priority/%s"
  [api-key {:keys [id] :as req}]
  (doapi api-key [id] {:priority (clj->csharp req)}))

(defapi delete-priority!
  :get "/v1/priority/%s/delete"
  [api-key {:keys [id]}]
  (doapi api-key [id] nil))

;;---------------------------------------------------------------------------;;

(defapi create-adtype!
  :post "/v1/adtypes"
  [api-key req]
  (doapi api-key {:adtype (clj->csharp req)}))

(defapi delete-adtype!
  :get "/v1/adtypes/%s/delete"
  [api-key {:keys [id]}]
  (doapi api-key [id] nil))

;;---------------------------------------------------------------------------;;

(deflist "channel")
(defget  "channel")
(deflist "zone")
(defget  "zone")
(deflist "site")
(defget  "site")
(deflist "priority")
(defget  "priority")

(deflist "channelSite")

(defapi channel-site!
  :get "/v1/channel/%s/site/%s"
  [api-key {:keys [c-id s-id]}]
  (doapi api-key [c-id s-id] nil))

