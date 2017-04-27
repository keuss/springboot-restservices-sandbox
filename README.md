# springboot-restservices-sandbox

springboot-restservices-sandbox with

 - REST basic controller
 - Spring DATA add PostgreSQL database + h2 for testing
 - Swagger2
 
## Build and RUN

 - `mvn clean package`
 - java -jar target/springboot-restservices-sandbox-0.1.0.jar
 
## Tests services & controllers

 - TI with PostgreSQL (see application.properties)
 - TU with H2 (see test.properties and test.sql for loading H2)
 - Swagger : http://localhost:8080/swagger-ui.html
