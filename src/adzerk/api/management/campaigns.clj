(ns adzerk.api.management.campaigns
  (:require [adzerk.api :refer (defapi deflist defget)]
            [adzerk.helpers :refer (clj->csharp)]))

(defapi create-advertiser!
  :post "/v1/advertiser"
  {:input-data :form}
  [req]
  (doapi {:advertiser (clj->csharp req)}))

(defapi update-advertiser!
  :put "/v1/advertiser/%s"
  {:input-data :form}
  [{:keys [id] :as req}]
  (doapi [id] {:advertiser (clj->csharp req)}))

(defapi delete-advertiser!
  :get "/v1/advertiser/%s/delete"
  {:input-data :none}
  [id]
  (doapi [id] nil))

;;---------------------------------------------------------------------------;;
;; min keys for update camp: :id :is-archived :advertiser-id :start-date :name

(defapi create-campaign!
  :post "/v1/campaign"
  {:input-data :form}
  [req]
  (doapi {:campaign (clj->csharp req)}))

(defapi update-campaign!
  :put "/v1/campaign/%s"
  {:input-data :form}
  [{:keys [id] :as req}]
  (doapi [id] {:campaign (clj->csharp req)}))

(defapi delete-campaign!
  :get "/v1/campaign/%s/delete"
  {:input-data :none}
  [id]
  (doapi [id] nil))

;;---------------------------------------------------------------------------;;

(defapi update-flight!
  :put "/v1/flight/%s"
  {:input-data :form}
  [{:keys [id] :as req}]
  (doapi [id] {:flight (clj->csharp req)}))

;;---------------------------------------------------------------------------;;

(defapi create-creative!
  :post "/v1/creative"
  {:input-data :form}
  [req]
  (doapi {:creative (clj->csharp req)}))

(defapi update-creative!
  :put "/v1/creative/%s"
  {:input-data :form}
  [{:keys [id] :as req}]
  (doapi [id] {:creative (clj->csharp req)}))

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
;   {:input-data ???}
;   []
;   (doapi))

; (defapi list-ads!
;   :get "/v1/flight/819865/creatives"
;   {:input-data ???}
;   [api-key]
;   (doapi))

; (defapi ad!
;   :get "/v1/flight/819865/creative/%s"
;   {:input-data ???}
;   [{:keys [id]}]
;   (doapi [id] nil))

