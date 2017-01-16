# New Resource
Lets learn to create a new REST Resource

## URL Path Creation
Create a class and annotate with `@Path('<path>')`

```java
@Path("/profile")
public class ProfileResource {
	...
}
```

=============

## Parameters
* All paramters are **case sensitive**

### 1 PathParam
* this would be used to read url path parameters. Example. in case of 'webapi\profiles\1'.  Here 1 is path param and would be used to get profile which matches to id 1.
* `@Path` value needs to be set as `@Path('{profileId}')` where 'profileId' would be our path param place holder and would be received at method arguments by using `@PathParam('profileId') <data type> <variable>`
  * we need to use same for `PathParam` which is used at `@Path`
  
```java
@Path("/{profileId}")
public Profile getProfile(@PathParam("profileId") String profileId) {
	...
}
```
  
### 2 QueryParam
* Query Parameters are **case sensitive**
* this would be used to read url query parameters.  Example. in case of 'webapi\profiles?filterByUsername=bhupal', this would return all profiles which are matched to bhupal
* Query Parameters are sepearted by `&` and these sepearted from url by using `?`
* `@QueryParam("byUsername") String byUsername` would be used to read `byUsername` query parameter 

```java
public List<Profile> getProfiles(@QueryParam("byUsername") String byUsername) {
	...
}
```

```url
http://localhost:8080/messanger/webapi/profile?byUsername=all
```

### 3 MatrixParam
* Matrix Parameters are different than Query Parameters
* Matrix Parameters are sepearted by semicolon `;`
* `@MatrixParam("param1")String parm1` would be used to read `param1`

```java
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/params")
public List<Profile> getProfiles2(
	@MatrixParam("param1")String parm1,
	@MatrixParam("param2")String parm2) 
{
	...
}
```

```url
http://localhost:8080/messanger/webapi/profile/params;param1=test;param1=test2
```

### 4 HeaderParam
* Header Parameters are not passed as header content instead of url
* `@HeaderParam("headerParam1")String headerParam1` would be used to read `headerParam1`

```java
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/params")
public List<Profile> getProfiles2(
	@HeaderParam("param1")String parm1,
	@HeaderParam("param2")String parm2) 
{
	...
}
```

### 5 CookieParam
* Cookie Parameters are used to read cookies which are set by server
* `@CookieParam("headerParam1")String cookieName` would be used to read `cookieName`

```java
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/params")
public List<Profile> getProfiles2(
	@CookieParam("JSESSIONID")String jession) 
{
	...
}
```

### 5 Context Parameters
* If we have many header parameters or cookies, need to read all of those using many HeaderParam and CookieParam
* instead we can read all of those using `Context` parameter
* `@Context UriInfo uriInfo` or `@Context httpHeaders httpHeaders`

```java
@GET
@Path("/context")
@Produces(MediaType.TEXT_PLAIN)
public String logContext(
	@Context UriInfo uriInfo,
	@Context HttpHeaders httpHeaders) 
{
	...
}
```

### 6 Bean Parameters
* if we have multiple query parameters, for example year, month and day.  then we can use bean parameters.
* Create a POJO with Query Parameters and use `BeanParam <POJO> <variable>`

```url
http://localhost:8080/messanger/webapi/profile/filter?day=15&month=1&year=2017
```

```java
@GET
@Path("/filter")
@Produces(MediaType.TEXT_PLAIN)
public String logFilter(@BeanParam DateFilter datefilter) {
}
```

```java
public class DateFilter {
	@QueryParam("year")
	int year;

	@QueryParam("month")
	int month;
	
	@QueryParam("day")
	int day;

	// setters & getters
}
```


=============

## Resource Data Object 
* Create a POJO and annotate with `@XmlRootElement`.  Whenever resource method returns this object and as per `Produces` and annotation arguments, either XML or JSON would be created.
* Must be implemented **public no argument constuctor** else conversion would fail

```java
@XmlRootElement
public class Profile {
	public Profile() {
		super();
	}
	
	...
}
```

## Resource Creation
* Resources would be **re-created** whenever there is a request, which means one request one new resource object
  * which means, if we want to know how many times that resource had been accessed
  * Simple private counter wouldnt help, as resource object would be created everytime
* How do we solve this?
  * By using `@Singleton` annotation at class declaration 
  * Resource would be singleton and **only one object would get created** and would be reused on every request
  * **There is catch here**, as resource become singleton, we need to be very careful while declaring private memeber variables


### Http Get Implementation - Get Data
* Http Request should be having method type as GET
* this would be used to get information from server either in the form of xml or json as per the request
* Resource method would be annotated with `@GET` or `@GET("<path>")` and use `@Produces(MediaType.XXXXXXXXXX)` to return either xml or json
  * MediaType.APPLICATION_JSON
  * MediaType.APPLICATION_XML
  * MediaType.TEXT_PLAIN

```java
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/{profileId}")
public Profile getProfile(@PathParam("profileId") String profileId) {
	...
}
```



### Http Post Implementation - Add New Data
* Http Request should be having method type as POST
* this would be used to add the data
* Resource method would be annotated with `@POST` or `@POST("<path>")` and would be received as simple text at method arguments
  * to receive in the form of object, use `@Consumes(MediaType.XXXXXXXXXX)` to consume the data and for method arguments use the object which is required (which is resource data object)

```java
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Profile addProfile(Profile profileObj) {
	...
}
```
  
### Http Put Implementation - Update Data
* Http Request should be having method type as UPDATE
* this would be used to update data
* Resource method would be annotated with `@UPDATE` or `@UPDATE("<path>")` 
  * to receive in the form of object, use `@Consumes(MediaType.XXXXXXXXXX)` to consume the data and for method arguments use the object which is required (which is resource data object)

```java
@PUT
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Profile updateProfile(Profile profileObj) {
	...
}
```

### Http Delete Implementation - Delete Data
* Http Request should be having method type as DELETE
* this would be used to update data
* Resource method would be annotated with `@DELETE` or `@DELETE("<path>")` 
  * Use `PathParam` to get Id
  * Use `@Consumes(MediaType.XXXXXXXXXX)` to consume the data and for method arguments use the object which is required (which is resource data object)

```java
@DELETE
@Path("/{profileId}")
@Produces(MediaType.APPLICATION_JSON)
public Profile updateProfile(@PathParam("profileId") String profileId) {
	...
}
```

## Sub Resource

### Get Messages
* For Example. url is 'http://localhost:8080/messanger/webapi/profile/1/messages/'

* `profile` resource creation
  * Dont use Http Method declarations
  * No Need of `@Produces` as sub resource would return
* Sub Resource has all features like main resource, which means
  * GET, POST, UPDATE, DELETE and all
  * all kind of Parameters can be used
  
```java
@Path("/profile")
public class ProfileResource {
	@Path("/{profileId}/messages")
	public MessagesResource getMessages(@PathParam("profileId") String profileId) {
		...
		
		return new MessagesResource();
	}
}
```
```java
@Path("/")
public class MessagesResource {
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Message> getMessages(@PathParam("profileId") String profileId) {
		...
	 }
}
```

## Reponse Status 
Along with updated entity/object, we could send Statuses.
Example. sending Response while posting profile

```java
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response addProfile(Profile profileObj, @Context UriInfo uriInfo)
		throws URISyntaxException {
	Profile profile = DataService.getInstance().addProfile(profileObj);

	ResponseBuilder reponse = null;
	if (profile != null) {
		URI uri = uriInfo.getAbsolutePathBuilder()
				.path(String.valueOf(profile.getId())).build();
		reponse = Response.created(uri).entity(profile);
	} else
		reponse = Response.status(Status.BAD_REQUEST);

	return reponse.build();
}
```

## Error Message
* How do we need to create and handle custom exceptions?
  * Create an exception class by extending RuntimeException
  * Create a handler to handle the message, else we would be getting 'Internal Server Error'

### Custom Exception - No Data Found Exception
* Whenever data is not found, below Exception object would be thrown as per service

```java
public class DataService {
	public Profile getProfileById(String id) {
		...
		
		throw new NoDataFoundException("No Data Found for id '" + id + "'");
	}
}
```
```java
public class NoDataFoundException extends RuntimeException {
	public NoDataFoundException(String message) {
		super(message);
	}
}
```

### Exception Handler
If we didnt handle any Exception, then we would be 'Internal Server Error'
* Create a new class by implementing `ExceptionMapper` and implement `toResponse` method
* Unless we annotate the class with `@Provider`, this mapper would not get invoked

```java
@Provider	
public class DoDataFoundExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<NoDataFoundException> {
	@Override
	public Response toResponse(NoDataFoundException excp) {
		return Response.status(Status.NOT_FOUND).build();
	}
}
```

* This would send only error status that not found, but we still default web server error page. How to handle this?

#### Create a POJO to hold Error Message
```java
public class ErrorMessage {
	int errorCode;
	String errorMessage;
	String documentation;

	public ErrorMessage() {
	}

	public ErrorMessage(int errorCode, String errorMessage, String documentation) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.documentation = documentation;
	}
}
```

#### Handle the exception
* while handling the exception, create error message and add to Reponse

```java
@Provider	
public class DoDataFoundExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<NoDataFoundException> {
	@Override
	public Response toResponse(NoDataFoundException excp) {
		ErrorMessage message = new ErrorMessage(404, excp.getMessage(), "http://com.ranga.webservices/");
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}
}
```

## Is ther any other way to handle exceptions?
* Yes, i.e., `javax.ws.rs.WebApplicationException`
* Whenever we raise `WebApplicationException` instead of `RuntimeException`
* `WebApplicationException` would take care of everything which avoid `ExceptionMapper` 
* Unlike `ExceptionMapper`, we create `Response` object at service and passed to `WebApplicationException` constructor as an argument
```java
public class DataService {
	public Profile getProfileById(String id) {
		...
		
		ErrorMessage message = new ErrorMessage(404, "No Data Found for id '" + id + "'", "http://com.ranga.webservices/");
		Response response = Response.status(Status.NOT_FOUND).entity(message) .build();
		throw new WebApplicationException(response);	
	}
}
```

## HATEOAS

### Implementation of Links
* Create an POJO for holding link and relation
* Add links array property to Resource Data
* Before returning the resource data and links
* We can add other relations for sub resource as well

#### Adding a Resource
```java
@Path("/profile")
public class ProfileResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{profileId}")
	public Profile getProfile(@PathParam("profileId") String profileId, @Context UriInfo uriInfo) {
		...		
		String selfUri = uriInfo.getBaseUriBuilder()	// http://localhost:8080/messanger/webapi
			.path(ProfileResource.class)				// /profile
			.path(String.valueOf(profile.getId()))		// /1
			.build()
			.toString();
		profile.getLinks().add(new Link(selfUri, "self"));
		
		return profile; 
	}
	...
}
```
#### Adding Sub Resource
* this is not same as Resource as Sub Resource would have `@Path("/")` which would not work for creating link
* How do we solve this?
  * By using sub resource method which helped to call Sub Resource like `path(<Resource>.class, "<subResourceMethodName>")`
  * Does this solve our problem?
    * If you think Yes, then you are wrong
	* Why?  subResourceMethod has place holder for resource id.
	* we would resolve place holder problem with the help of `resolve("<place hodler>", "<value>")` method
	
Here is the sub resource url creation
```java
	...
	public Profile getProfile(@PathParam("profileId") String profileId, @Context UriInfo uriInfo) {
		...
		String messageUri =  uriInfo.getBaseUriBuilder()		// http://localhost:8080/messanger/webapi
			.path(ProfileResource.class)						// /profile
			.path(ProfileResource.class, "getMessages")			// /{profileId}/messages
			.resolveTemplate("profileId", profileId)			// profileId ==> 1
			.build().toString();
		profile.getLinks().add(new Link(messageUri, "messages"));
		...
	}
	...
```

## Content Nagotiation
* In our case we are getting only Json as we implem for Json
* What if we need XML format, then we would have implemented a new method to return XML at `@Produces`
  * Instead we can add another format like `@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })`
  * This would support both Json and XML
  * How does Rest Architecture identify which format that client requested?
    * Requested format would be part of Request of Header at `Accept`
	* **Rest Service automatically check `Accept` header parameter** and generates the request object
  * No need of method body change

* We can send the request in the form of Json using `@Consumes` in case of POST, PUT and other
* Can we request in different formates?
  * Yes, by defining two differnt types like `@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })`
  * How does Rest Architecture identify which format that client sent over request?
    * Requested format would be part of Request of Header at `Content-Type`
    * **Rest Service automatically check `Content-Type` header parameter** and processes the request object
  * No need of method body change

| Header Parameter | Annotation | Action |
| ---------------  |:----------:| ------:|
| Accept           | Produces   | Generates |
| Content-Type     | Consumes   | Processes |

```java
@Path("/profile")
public class ProfileResource {
	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addProfile(Profile profileObj) {
		...
	}
}
```

## Convertors
* In case of simple date and which is keep on changing everyday
* How do we covert 'today' to date?
  * We can acheive this by using custom convertors
  * Jersy would check all providers to resolve the convertor  
  * Read as normal PathParam with custom resource data
  
### ParamConvertorProvider
* create a class by extending `ParamConvertorProvider` interface
* implement `getConvertor` method
* Create inline class which extends `ParamConvertor<T>` class and override `fromString` and `toString` methods
  * inside `fromString` method, create a custom pojo object and set required properties
  * cast the custom pojo using `Class<T>`

Example: *http://localhost:8080/messanger/webapi/date/today* would result as below
```json
{
  "day": 16,
  "month": 0,
  "year": 2017
}
```
  
```java
@Provider
public class MyDateConvertorProvider implements ParamConverterProvider {
	@Override
	public <T> ParamConverter<T> getConverter(final Class<T> rawType,
			Type genericType, Annotation[] annotations) {
		return new ParamConverter<T>() {
			@Override
			public T fromString(String value) {
				Calendar requestedDate = Calendar.getInstance();
				if (value.equals("tomorrow")) {
					requestedDate.add(Calendar.DATE, 1);
				} else if (value.equals("yesterday")) {
					requestedDate.add(Calendar.DATE, -1);
				}

				MyDate date = new MyDate(requestedDate.get(Calendar.YEAR),
						requestedDate.get(Calendar.MONTH),
						requestedDate.get(Calendar.DATE));

				return rawType.cast(date);
			}

			@Override
			public String toString(T obj) {
				if (obj != null)
					return obj.toString();
				return null;
			}
		};
	}
}
```
```java
@Path("/date")
public class DateResource {	
	@GET
	@Path("/{dateFilter}")
	@Produces(MediaType.APPLICATION_JSON)
	public MyDate getDate(@PathParam("dateFilter") MyDate date) {
		return date;
	}
}
```
```java
public class MyDate {
	int year;
	int month;
	int day;
	...
}
```

## Message Body
* along with http request or response, message body would be sent along with data
* Message Body is different when compared with Parameters
* To read message body, we need message readers and writers

### Message Body Writer
* Example. while using POST or PUT url, you might be sending some data along with path
  * url path has path parameters
  * json data which is nothing but message body, generally post json data is message body
  
* Example. if you want to implement a rest api for returning date in plan text like below,   would result an error at server.  which is nothing but `MessageBodyWriter` not found for `java.util.Date`.
```java
@Path("/test")
public class MyResource {	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Date getDate() {
		return Calendar.getInstance().getTime();
	}
}
```
### How do we solve?
By writing a custom `MessageBodyWriter` to convert `java.util.Date` to String (in our case)

* Create a class which implments `MessageBodyWriter<T>` and instead of Generic type use `java.util.Date`
* implment all necessary methods
  * `getSize` method id deprecated, so just return -1
  * `isWriteable` method is responsible to let the Jersy know whether this class/provider can be used for Message body writer
  * `writeTo` method is responsible for message body writing
    * convert the data object from argument one which Date object
	* add the ouptput to `OutputStream` in the form of bytes
* Message body writers are different for differnt message types
  * in our case, we are writing MessageBodyWriter for text
  * add `@Produces(MediaType.TEXT_PLAIN)` to handler only text
	
```java
@Provider
@Produces(MediaType.TEXT_PLAIN)
public class DateMessageBodyWriter implements MessageBodyWriter<Date> {
	@Override
	public long getSize(Date arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		// Deprecated
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		return Date.class.isAssignableFrom(type);
	}

	@Override
	public void writeTo(Date date, Class<?> type, Type type1, Annotation[] anntns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) 
		throws IOException, WebApplicationException
	{
		out.write(date.toString().getBytes());
	}
}
```

## References
* [ Developing RESTful APIs with JAX-RS] (https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn)
* [Advanced JAX-RS] (https://www.youtube.com/playlist?list=PLqq-6Pq4lTTY40IcG584ynNqibMc1heIa)