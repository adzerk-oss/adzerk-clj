(ns adzerk.api.management.campaigns
  (:require [adzerk.api :refer (defapi deflist defget)]
            [adzerk.helpers :refer (clj->csharp)]))

(defapi create-advertiser!
  :post "/v1/advertiser"
  [api-key req]
  (print "creating advertiser")
  (doapi api-key {:advertiser (clj->csharp req)}))

(defapi update-advertiser!
  :put "/v1/advertiser/%s"
  [api-key {:keys [id] :as req}]
  (print "updating advertiser")
  (doapi api-key [id] {:advertiser (clj->csharp req)}))

(defapi delete-advertiser!
  :get "/v1/advertiser/%s/delete"
  [api-key {:keys [id]}]
  (doapi api-key [id] nil))

;;---------------------------------------------------------------------------;;
;; min keys for update camp: :id :is-archived :advertiser-id :start-date :name

(defapi create-campaign!
  :post "/v1/campaign"
  [api-key req]
  (doapi api-key {:campaign (clj->csharp req)}))

(defapi update-campaign!
  :put "/v1/campaign/%s"
  [api-key {:keys [id] :as req}]
  (doapi api-key [id] {:campaign (clj->csharp req)}))

(defapi delete-campaign!
  :get "/v1/campaign/%s/delete"
  [api-key {:keys [id]}]
  (doapi api-key [id] nil))

;;---------------------------------------------------------------------------;;

(defapi update-flight!
  :put "/v1/flight/%s"
  [api-key {:keys [id] :as req}]
  (doapi api-key [id] {:flight (clj->csharp req)}))

;;---------------------------------------------------------------------------;;

(defapi create-creative!
  :post "/v1/creative"
  [api-key req]
  (doapi api-key {:creative (clj->csharp req)}))

(defapi update-creative!
  :put "/v1/creative/%s"
  [api-key {:keys [id] :as req}]
  (doapi api-key [id] {:creative (clj->csharp req)}))

;;---------------------------------------------------------------------------;;

(deflist "advertiser")
(defget  "advertiser")
(deflist "campaign")
(defget  "campaign")
(deflist "flight")
(defget  "flight")
(defget  "creative")

; TODO: make these endpoints take parameters
; (defapi list-creatives!
;   :get "/v1/advertiser/87479/creatives"
;   [api-key]
;   (doapi api-key))

; (defapi list-ads!
;   :get "/v1/flight/819865/creatives"
;   [api-key]
;   (doapi api-key))

; (defapi ad!
;   :get "/v1/flight/819865/creative/%s"
;   [api-key {:keys [id]}]
;   (doapi api-key [id] nil))

