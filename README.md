# List of Applications
* [messanger/readme.md] (JersyRest/readme.md)
* [sb-practice/readme.md] (SpringBoot/readme.md)

## messanger/Rest API
We are going to implement REST API using Jersy

*  setting up first rest api web application using 'org.glassfish.jersey.archetypes:jersey-quickstart-webapp'
### How to generate eclipse web project using maven
```sh
mvn eclpse:eclipse -Dpwtverison=2.0
```

* If you are getting any build error, please check Eclispe - Java Build Path
  * If you are getting error at M2_REPO user variable, please configure.
  * If Maven Local Repository path is configured wrongly, please configure correct path.
  * If eclipse not letting you to chagne M2_REPO variable, then configure __Eclipse -> Preferences -> Maven -> User Settings__ with __<MavenInstallPath>\conf\settings.xml__ whcih would help you to resolve build issues

>  __Note__ - this is for Java Version 1.7

### apache tomcat server configuration for Maven
we need to configure the server on 'apache-maven-3.1.1\conf\settings.xml'.  username and password from 'apache-tomcat-6.0.41\conf\tomcat-users.xml'
```xml
        <servers>
          <server>
             <id>tomcatserver</id>
             <username>admin</username>
             <password>s3cret</password>
          </server>
        </servers>
```

> __tomcat 7__ : please ensure that tomcat picked Java Higher version else you will get magor.minor expcection [51]

## sb-practice/Spring Boot
We are going to learn spring boot.


# References
* Koushik course - walkthrough