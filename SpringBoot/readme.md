# Learning Spring Boot
* We are going to look at implementing Restful API end to end 
* Spring boot features
* Spring boot configuration

## Prerequisite

### Rest API
1. Basic knowledge on Web Server setup
2. Basic knowledge on Request/Response of Web server
3. JSON/XML
4. Spring Core

### Tools
1. JDK 1.7
2. Eclipse
3. Maven
4. Google Chrome or any advanced Browser
5. Postman or any other tool

## Contents

---

## Spring Boot Project Creation
* Spring Boot Project can be created in different ways
  * Spring Initailizr
  * Maven
  * Spring STS
  
### Project Creation
* Here, I am using Spring Initailizr
  * [http://start.spring.io/] (http://start.spring.io/)
  * Click on Full version
  * Provide 'Group', 'Artifact' and 'Dependencies', so on 
  * In this case, i am choosing __'Spring Web', 'Spring JPA' and 'Apache Derby'__
  * Generate Project which would download the spring boot application

__Spring Boot Project has been created__
  
### Running Maven
* Now, we have spring boot project.  All we need to do is, run Maven
  * clean - cleaning project
  * compile - compiling code which is under /src folder
  * package - generates the executable jar or deployable war depends upon the pom.xml configuration.  In our case, jar
  * __spring-boot:run__ - This is Spring Boot maven target to execute jar or start a web server

After running below command, Maven would take some time to download dependencies (this would happen only once and triggers if any new dependencies are included).
After build, compile and testing; application starts.

```
mvn clean compile package spring-boot:run
```

Type http://localhost:8080 to check the application, you would be getting a default error page which is 404-NotFound as we dont have any page to render
  
---
# References
* [Spring Boot Videos] (https://www.youtube.com/playlist?list=PLqq-6Pq4lTTbx8p2oCgcAQGQyqN8XeA1x)