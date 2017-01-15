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


## References
* [Youtube Videos] (https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn)