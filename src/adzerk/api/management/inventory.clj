(ns adzerk.api.management.inventory
  (:require [adzerk.api :refer (defapi deflist defget)]
            [adzerk.helpers :refer (clj->csharp)]))

(defapi create-site!
  :post "/v1/site" :form
  [req]
  (doapi {:site (clj->csharp req)}))

(defapi update-site!
  :put "/v1/site/%s" :form
  [{:keys [id] :as req}]
  (doapi [id] {:site (clj->csharp req)}))

(defapi delete-site!
  :put "/v1/site/%s" :form
  [{:keys [id] :as req}]
  (doapi [id] {:site (clj->csharp (assoc req :is-deleted true))}))

;;---------------------------------------------------------------------------;;

(defapi add-site-to-channel!
  :post "/v1/channelSite" :form
  [req]
  (doapi {:channelSite (clj->csharp req)}))

(defapi update-site-channel-map!
  :put "/v1/channelSite" :form
  [{:keys [channelid] :as req}]
  (doapi {:channelSite (clj->csharp req)}))

(defapi remove-site-from-channel!
  :get "/v1/channel/%s/site/%s/delete" :none
  [{:keys [channel-id site-id] :as req}]
  (doapi [channel-id site-id] nil))

;;---------------------------------------------------------------------------;;

(defapi create-channel!
  :post "/v1/channel" :form
  [req]
  (doapi {:channel (clj->csharp req {:cpm 0 :engine 0})}))

(defapi update-channel!
  :put "/v1/channel/%s" :form
  [{:keys [id] :as req}]
  (doapi [id] {:channel (clj->csharp req {:cpm 0 :engine 0})}))

(defapi delete-channel!
  :get "/v1/channel/%s/delete" :none
  [id]
  (doapi [id] nil))

;;---------------------------------------------------------------------------;;

(defapi create-priority!
  :post "/v1/priority" :form
  [req]
  (doapi {:priority (clj->csharp req)}))

(defapi update-priority!
  :put "/v1/priority/%s" :form
  [{:keys [id] :as req}]
  (doapi [id] {:priority (clj->csharp req)}))

(defapi delete-priority!
  :get "/v1/priority/%s/delete" :none
  [id]
  (doapi [id] nil))

;;---------------------------------------------------------------------------;;

(defapi create-adtype!
  :post "/v1/adtypes" :form
  [req]
  (doapi {:adtype (clj->csharp req)}))

(defapi delete-adtype!
  :get "/v1/adtypes/%s/delete" :none
  [id]
  (doapi [id] nil))

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

(defapi channel-site
  :get "/v1/channel/%s/site/%s" :none
  [{:keys [channel-id site-id]}]
  (doapi [channel-id site-id] nil))

