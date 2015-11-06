(ns adzerk.test-helpers)

(defn items [adzerk-resp]
  (get adzerk-resp "items"))

(defn deleted? [{is-del? "IsDeleted"}]
  (boolean is-del?))

(def not-deleted? (complement deleted?))

(defn excluding-deleted? [adzerk-resp]
  (every? not-deleted? (items adzerk-resp)))

(defn archived? [{is-archived? "IsArchived"}]
  (boolean is-archived?))

