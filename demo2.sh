#!/bin/sh

application_1_id=c719c823-e302-4d60-8d89-233f2750f277

curl \
  -v \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"familyName":"Gregory","givenNames":"Jane"}' \
  "http://localhost:8080/applications/${application_1_id}/personal-details" \
| jq
