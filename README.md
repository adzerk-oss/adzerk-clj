# adzerk-clj

[![Clojars Project](https://img.shields.io/clojars/v/adzerk/adzerk-clj.svg)](https://clojars.org/adzerk/adzerk-clj)

> **WARNING: This library is in alpha!** Use at your own peril until version 0.1.0+. You have been warned. :ghost:

## Usage

### `ADZERK_API_HOST`

By default, this library uses `https://api.adzerk.net` as the base URL for Adzerk API requests. To change this, set `ADZERK_API_HOST` to the base URL you want to use.

### `ADZERK_API_KEY`

Set the environment variable `ADZERK_API_KEY` to a valid API key for your network. `adzerk.api/*api-key*` will be set to this value.

Alternatively, you can bind `adzerk.api/*api-key*` to the API key you want to use:

```clojure
(binding [adzerk.api/*api-key* MY_API_KEY]
  (do-stuff))
```

This may be useful if your application needs to use multiple Adzerk API keys.

## TODO: tweak the way we run tests, rewrite the stuff below

For running the tests you need to set the test-* variables. If you use the
defaults make sure you grab my (jarrod.adzerk.net) api key.

Then run boot repl in the directory w/ this file and (load-file "api-smoker").
To run all the tests run (clojure.tests/run-tests).

