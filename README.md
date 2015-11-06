# adzerk-clj

This is the beginning of a tool for smoke testing the API, and doing integration tests.

To use it you need to set two environment variables:
  - ADZERK_API_HOST
  - ADZERK_API_KEY

For running the tests you need to set the test-* variables. If you use the
defaults make sure you grab my (jarrod.adzerk.net) api key.

Then run boot repl in the directory w/ this file and (load-file "api-smoker").
To run all the tests run (clojure.tests/run-tests).

