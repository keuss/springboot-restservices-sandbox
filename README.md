# springboot-restservices-sandbox

springboot-restservices-sandbox with :

 - REST controllers (GET & POST)
 - Spring DATA add PostgreSQL database + h2 for testing (branch `h2-runtime` for h2 in runtime)
 - Swagger2
 
## Build and RUN

 - `mvn clean package`
 - `java -jar target/springboot-restservices-sandbox-0.1.0.jar` (use Embedded Tomcat), see http://localhost:8080/api/users (to change port : `-Dserver.port=XXXX`)
 
## Tests services & controllers

 - TU with H2 (see test.properties and test.sql for loading H2)
 - Swagger : http://localhost:8080/api/swagger-ui.html
 
## DATA h2

```
INSERT INTO public.users(id, name, email, userdetail) VALUES (0, 'gui', 'gui@gmail.com', '{"postsNb": 10, "followersNb": 3, "subscribersNb": 2, "followers": [1, 2, 3], "subscribers": [10, 20]}');
```

## JSON user

```
  {
    "id": 0,
    "name": "gui",
    "email": "gui@gmail.com",
    "userDetail": {
      "postsNb": 11,
      "followersNb": 2,
      "followingNb": 0,
      "suggestions": [2, 3]
    }
  }
  
  Path examples :
  
  [{
  	"op": "replace",
  	"path": "/userDetail/followingNb",
  	"value": 111
  }]

  Or :
  
  [{
   "op": "replace",
   "path": "/userDetail",
   "value": {
    "postsNb": 111,
    "followersNb": 222,
    "followingNb": 333,
    "suggestions": [444]
   }
  }]
  
  
```

## Links

 - https://github.com/spring-guides/gs-accessing-data-jpa
 - https://github.com/spring-guides/gs-rest-service
 - https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
 - https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
 - https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html
 - http://jsonpatch.com/
