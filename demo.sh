#!/bin/sh

curl \
  -v \
  -H 'Accept: application/json' \
  'http://localhost:8080/applications'

application_1_id=$(uuidgen | tr '[:upper:]' '[:lower:]')

curl \
  -v \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"id":"'${application_1_id}'"}' \
  'http://localhost:8080/applications'

curl \
  -v \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"familyName":"Smith","givenNames":"Jane"}' \
  "http://localhost:8080/applications/${application_1_id}/personal-details"

application_1_document_1_id=$(uuidgen | tr '[:upper:]' '[:lower:]')

curl \
  -v \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"id": "'${application_1_document_1_id}'", "name":"Bank statement","content":"asdfasdf"}' \
  "http://localhost:8080/applications/${application_1_id}/documents"

application_1_document_2_id=$(uuidgen | tr '[:upper:]' '[:lower:]')

curl \
  -v \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"id": "'${application_1_document_2_id}'", "name":"Payslip","content":"qwerqwer"}' \
  "http://localhost:8080/applications/${application_1_id}/documents"

application_2_id=$(uuidgen | tr '[:upper:]' '[:lower:]')

curl \
  -v \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"id":"'${application_2_id}'"}' \
  'http://localhost:8080/applications'

curl \
  -v \
  -H 'Accept: application/json' \
  'http://localhost:8080/applications'
