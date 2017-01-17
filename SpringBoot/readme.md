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
5. RESTful API
6. Spring MVC (Optional)

### Tools
1. JDK 1.7
2. Eclipse
3. Maven
4. Google Chrome or any advanced Browser
5. Postman or any other tool

## Contents
1. [Spring Boot Project Creation] (#spring-boot-project-creation)
2. [Rest API Implementation] (#rest-api-implementation)
3. [Spring Boot Configuration] (#spring-boot-configuration)


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

```sh
mvn clean compile package spring-boot:run
```

Type [http://localhost:8080] (http://localhost:8080) to check the application, you would be getting a default error page which is 404-NotFound as we dont have any page to render

[Contents] (#contents)

### Eclipse Project creation
We are going to create eclipse project using Maven eclipse plugin
* We are going to pass `pwtversion` to create Web Eclipse Project
* Eclipse project creation would be created by assuming M2_REPO variable is configured, else please create M2_REPO user variable with Maven Local Repository Path

```sh
mvn eclpse:eclipse -Dpwtverison=2.0
```

## Rest API Implementation
Now, we are going to implement Rest API

### Simple Rest API - Greeting
* Create a class and annotate with `@RestController`
* Create a method which returns String and annotate with `@RequestMapping("<url path>")`
  * If we have not specified any Request Method, by default it is __Get__
* Rest Service created.  Yes, It is created.
* Run maven build and run the application as mentioned at [Running Maven](#running-maven)
* Access the url [http://localhost:8080/hello] (http://localhost:8080/hello), you would get __Welcome to Rest Services__

```java
@RestController
public class GreetingsRestController {
	@RequestMapping("/hello")
	public String sayHello() {
		return "Welcome to Rest Services";
	}
}
```

### Spring MVC Service
* Lets create a server to handle Topics
* Create a class and annotate with `@Service`; Spring would consider this class as service and this service object is ready
* In our example, we are maintaining a temporaray list of topics which would replaced with database data
* Now, we can inject this service object whereever we need with the help of __Auto Wiring__

```java
@Service
public class TopicService {
	private List<Topic> topicList = Arrays.asList(new Topic("spring", "Spring Framework", "Spring Framework Description"),
			new Topic("hibernate", "Hibernate Framework", "Hibernate Framework Description"));
	
	public List<Topic> getAllTopics() {
		return topicList;
	}
}
```

### Returning JSON Objects
* Http Method is __GET__
* Simply return POJO object from Rest Method, automatically object would be converted to JSON

```java
@RestController
public class TopicsRestController {
	@Autowired
	TopicService topicService;
	
	@RequestMapping("/topics")
	public List<Topic> getTopics() {
		return topicService.getAllTopics();
	}
}
```

### Path Variable
* Http Method is __GET__
* In case of filter, we can use `PathVariable` to get filter value and can be used to get the required object

```java
@RestController
public class TopicsRestController {
	@Autowired
	TopicService topicService;

	@RequestMapping("/topics/{topicId}")
	public Topic getTopicById(@PathVariable("topicId") String topicId) {
		return topicService.getTopicById(topicId);
	}
}
```

### Request Body
* Http Method is __POST__
* In case of adding a new object or POST method implementation
  * need to add `RequestMethod` to `RequestMapping` along with path
  * `RequestBody` would read the json from Http Post Request and sets on topic object

```java
  	@RequestMapping(method=RequestMethod.POST, value = "/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}
```

* In case of udpate, we use the same way as adding a new object by passing updated json along id
* Http Method is __PUT__

```java
	@RequestMapping(method=RequestMethod.PUT, value = "/topics/{topicId}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable("topicId")String topicId) {
		topicService.updateTopic(topic, topicId);
	}
```

* In case of delete, it is same as above
* Http Method is __DELETE__

```java
	@RequestMapping(method=RequestMethod.DELETE, value = "/topics/{topicId}")
	public void remvoeTopic(@PathVariable("topicId")String topicId) {
		topicService.deleteTopic(topicId);
	}
```

[Contents] (#contents)


## Spring Boot Configuration
* Spring Boot takes [default configuration] (https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html) and runs on it
* If we want to change any configuration, then we need to define the configuration at either `application.properties` or `application.yml` for same property

> __Note:__ application.properties/yml should be available at classpath

* For Example, if we want to change web server port
  * override default configuration with `server.port` in `application.properties`
  * in our case, application.properties is located at `src\main\resources\application.properties`
  * this file would come along with basic setup with empty file

[Contents] (#contents)

---
# References
* [Spring Boot Videos] (https://www.youtube.com/playlist?list=PLqq-6Pq4lTTbx8p2oCgcAQGQyqN8XeA1x)

[Contents] (#contents)