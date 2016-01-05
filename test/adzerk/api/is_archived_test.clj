(ns adzerk.api.is-archived-test
  (:require [clojure.test :refer :all]
            [adzerk.test-helpers :refer :all]
            [adzerk.api.management.campaigns :refer :all]
            [adzerk.api.management.inventory :refer :all]))

; FIXME: this was copied over from Jarrod's test script, where @test-key is his
; adzerk API key. We should rewrite this in more general terms... perhaps we
; should write some test cases that create the archived thing, and then have
; subsequent tests test the thing that we created, and then have some more test
; cases test that we can delete the thing in order to clean up.

(deftest is-archived-tests
  (testing "I can request an archived"
    (is (archived? (campaign! @test-key {:id 326552})) "campaign.")
    (is nil "flight.")
  (testing "Using the management API I can"
    (is nil "archive a campaign.")
    (is nil "archive a flight.")
    (is nil "un-archive an archived campaign.")
    (is nil "un-archive an archived flight.")
    )
  (testing "By default requesting a list of"
    (is nil "campaigns includes only unarchived items.")
    (is nil "flights includes only unarchived items.")
    )
  (testing "I can request a list of archived"
    (is nil "campaigns.")
    (is nil "flights.")
    (is nil "flights for a campaign.")
    )))
