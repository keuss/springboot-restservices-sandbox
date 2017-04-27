# springboot-restservices-sandbox

springboot-restservices-sandbox with :

 - REST basic controller (GET & POST)
 - Spring DATA add PostgreSQL database + h2 for testing
 - Swagger2
 
## Build and RUN

 - `mvn clean package`
 - `java -jar target/springboot-restservices-sandbox-0.1.0.jar` (use Embedded Tomcat), see http://localhost:8080/users
 
## Tests services & controllers

 - TI with PostgreSQL (see application.properties)
 - TU with H2 (see test.properties and test.sql for loading H2)
 - Swagger : http://localhost:8080/swagger-ui.html
 
## DDL PostgreSQL

```
CREATE TABLE public.users
(
  id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass), -- user's internal id
  name character(20), -- user's name
  email character(40), -- user's mail
  CONSTRAINT id_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO "sandbox-user";
COMMENT ON COLUMN public.users.id IS 'user''s internal id';
COMMENT ON COLUMN public.users.name IS 'user''s name';
COMMENT ON COLUMN public.users.email IS 'user''s mail';
```

## Links

 - https://github.com/spring-guides/gs-accessing-data-jpa
 - https://github.com/spring-guides/gs-rest-service
