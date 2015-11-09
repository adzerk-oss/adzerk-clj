(ns adzerk.api.is-deleted-test
  (:require [clojure.test :refer :all]
            [adzerk.test-helpers :refer :all]
            [adzerk.api :refer (*api-key*)]
            [adzerk.api.management.campaigns :refer :all]
            [adzerk.api.management.inventory :refer :all]))

(deftest is-deleted-tests
  (testing "Listing endpoints do not include deleted items for"
    (is (excluding-deleted? (list-campaigns!   *api-key*)) "campaigns.")
    (is (excluding-deleted? (list-flights!     *api-key*)) "flights.")
    (is (excluding-deleted? (list-advertisers! *api-key*)) "advertisers.")
    (is (excluding-deleted? (list-creatives!   *api-key*)) "creatives.")
    (is (excluding-deleted? (list-zones!       *api-key*)) "zones.")
    (is (excluding-deleted? (list-channels!    *api-key*)) "channels.")
    (is (excluding-deleted? (list-ads!         *api-key*)) "ads.")
    (is (excluding-deleted? (list-sites!       *api-key*)) "sites.")
    (is (excluding-deleted? (list-prioritys!   *api-key*)) "priorities."))
  (testing "Get endpoints return a deleted"
    (is (deleted? (zone!       *api-key* test-zone))       "zone.")
    (is (deleted? (campaign!   *api-key* test-campaign))   "campaign.")
    (is (deleted? (creative!   *api-key* test-creative))   "creative.")
    (is (deleted? (flight!     *api-key* test-flight))     "flight.")
    (is (deleted? (advertiser! *api-key* test-advertiser)) "advertiser.")
    (is (deleted? (channel!    *api-key* test-channel))    "channel.")
    (is (deleted? (ad!         *api-key* test-ad))         "ad.")
    (is (deleted? (site!       *api-key* test-site))       "site.")
    (is (deleted? (priority!   *api-key* test-priority))   "priority.")))
