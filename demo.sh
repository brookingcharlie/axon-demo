#!/bin/sh

curl \
  -v \
  -H 'Accept: application/json' \
  'http://localhost:8080/applications'

curl \
  -v \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"id":"'$(uuidgen | tr '[:upper:]' '[:lower:]')'"}' \
  'http://localhost:8080/applications'

curl \
  -v \
  -H 'Accept: application/json' \
  'http://localhost:8080/applications'
