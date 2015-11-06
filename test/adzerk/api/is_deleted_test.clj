(ns adzerk.api.is-deleted-test
  (:require [clojure.test :refer :all]
            [adzerk.test-helpers :refer :all]
            [adzerk.api.management.campaigns :refer :all]
            [adzerk.api.management.inventory :refer :all]))

(deftest is-deleted-tests
  (testing "Listing endpoints do not include deleted items for"
    (is (excluding-deleted? (list-campaigns!   @test-key)) "campaigns.")
    (is (excluding-deleted? (list-flights!     @test-key)) "flights.")
    (is (excluding-deleted? (list-advertisers! @test-key)) "advertisers.")
    (is (excluding-deleted? (list-creatives!   @test-key)) "creatives.")
    (is (excluding-deleted? (list-zones!       @test-key)) "zones.")
    (is (excluding-deleted? (list-channels!    @test-key)) "channels.")
    (is (excluding-deleted? (list-ads!         @test-key)) "ads.")
    (is (excluding-deleted? (list-sites!       @test-key)) "sites.")
    (is (excluding-deleted? (list-prioritys!   @test-key)) "priorities.")
    )
  (testing "Get endpoints return a deleted"
    (is (deleted? (zone!       @test-key test-zone))       "zone.")
    (is (deleted? (campaign!   @test-key test-campaign))   "campaign.")
    (is (deleted? (creative!   @test-key test-creative))   "creative.")
    (is (deleted? (flight!     @test-key test-flight))     "flight.")
    (is (deleted? (advertiser! @test-key test-advertiser)) "advertiser.")
    (is (deleted? (channel!    @test-key test-channel))    "channel.")
    (is (deleted? (ad!         @test-key test-ad))         "ad.")
    (is (deleted? (site!       @test-key test-site))       "site.")
    (is (deleted? (priority!   @test-key test-priority))   "priority.")))
