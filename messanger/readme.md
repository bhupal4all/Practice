# New Resource
Lets learn to create a new REST Resource

## URL Path Creation
Create a class and annotate with `@Path('<path>')`

### Http Get Implementation
* this would be used to get information from server either in the form of xml or json as per the request
* Resource method would be annotated with `@GET` and use `@Produces(MediaType.XXXXXXXXXX)` to return either xml or json
  * MediaType.APPLICATION_JSON
  * MediaType.APPLICATION_XML
  * MediaType.TEXT_PLAIN

#### PathParam
* this would be used to read url path parameters. Example. in case of 'webapi\profiles\1'.  Here 1 is path param and would be used to get profile which matches to id 1.
* `@Path` value needs to be set as `@Path('{profileId}')` where 'profileId' would be our path param place holder and would be received at method arguments by using `@PathParam('profileId') <data type> <variable>`
  * we need to use same for `PathParam` which is used at `@Path`

### Http Post Implementation



## Json 
* Create a POJO and annotate with `@XmlRootElement`.  Whenever resource method returns this object and as per `Produces` and annotation arguments, either XML or JSON would be created.
* Must be implemented *public no argument constuctor* else conversion would fail

