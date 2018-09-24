# axon-demo

Axon demo with branches for event-sourced and JPA persistence

* To see Event Sourcing in action, browse the [master](https://github.com/brookingcharlie/axon-demo/tree/master) branch.
* To see use of a Standard Repository that persists only the current state, browse the [standard-repository](https://github.com/brookingcharlie/axon-demo/tree/standard-repository) branch.

## Usage

Run the `main` method as a Java application.

Access the API via HTTP at <http://localhost:8080/>.

Example requests using the `curl` command are in `demo.sh`.

## Development notes

### Viewing file-based database

Access the H2 console at <http://localhost:8080/h2_console/>

* Driver class: org.h2.Driver
* JDBC URL: jdbc:h2:file:./h2/testdb
* Username: sa
* Password: (blank)

Example query to see events:

```
SELECT PAYLOAD_TYPE, UTF8TOSTRING(PAYLOAD), TYPE, AGGREGATE_IDENTIFIER, SEQUENCE_NUMBER
FROM DOMAIN_EVENT_ENTRY
ORDER BY TIME_STAMP
```
