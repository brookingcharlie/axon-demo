# jpa-axon-demo

Axon demo using simple JPA persistence (not event sourcing)

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
