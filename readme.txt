this repo contains the Webservices which were tought by Koushik

Koushik - walkthrough applications

1.  setting up first rest api web application using 'org.glassfish.jersey.archetypes:jersey-quickstart-webapp'
    [info] mvn eclpse:eclipse -Dpwtverison=2.0 (this is for web eclipse project supporting files)
    [note] this is for Java Version 1.7
    [apache tomcat server] we need to configure the server on 'apache-maven-3.1.1\conf\settings.xml'.  username and password from 'apache-tomcat-6.0.41\conf\tomcat-users.xml'
        <servers>
          <server>
             <id>tomcatserver</id>
             <username>admin</username>
             <password>s3cret</password>
          </server>
        </servers>
    [tomcat 7] please ensure that tomcat picked Java Higher version else you will get magor.minor expcection [51]