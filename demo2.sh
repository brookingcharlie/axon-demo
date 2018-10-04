#!/bin/sh

application_1_id=74fb6350-ec67-4f7e-8f63-571451ed6edb

curl \
  -v \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"familyName":"Jones","givenNames":"Jane"}' \
  "http://localhost:8080/applications/${application_1_id}/personal-details" \
| jq
