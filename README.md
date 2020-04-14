# Haikunator Clojure

[![Release](https://img.shields.io/clojars/v/haikunator.svg?style=flat-square)](https://clojars.org/haikunator)
[![Build Status](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Fatrox%2Fhaikunatorclj%2Fbadge&style=flat-square)](https://actions-badge.atrox.dev/atrox/haikunatorclj/goto)
[![Coverage Status](https://img.shields.io/codecov/c/github/atrox/haikunatorclj.svg?style=flat-square)](https://codecov.io/gh/atrox/haikunatorclj)

Generate Heroku-like random names to use in your clojure applications.

## Usage
```clojure
(ns test
  (:require [haikunator :refer [haikunate]]))

(haikunate)
; => "lucky-mode-0284"

(haikunate {:token-length 6})
; => "purple-dream-251709"

(haikunate {:token-chars "HAIKUNATE"})
; => "sparkling-hat-IATI"

(haikunate {:token-length 0})
; => "sparkling-bread"

(haikunate {:delimiter "."})
; => "tight.poetry.7673"

(haikunate {:token-length 0 :delimiter " "})
; => "lingering bar"

(haikunate {:token-length 0 :delimiter ""})
; => "floralcredit"

```
