# List of Applications
* [messanger/readme.md](messanger/readme.md)

## messanger/Rest API

*  setting up first rest api web application using 'org.glassfish.jersey.archetypes:jersey-quickstart-webapp'
### How to generate eclipse web project using maven
```sh
mvn eclpse:eclipse -Dpwtverison=2.0
```

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

# References
* Koushik course - walkthrough