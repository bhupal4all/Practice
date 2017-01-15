# New Resource
Lets learn to create a new REST Resource

## URL Path Creation
Create a class and annotate with `@Path('<path>')`

## PathParam
* this would be used to read url path parameters. Example. in case of 'webapi\profiles\1'.  Here 1 is path param and would be used to get profile which matches to id 1.
* `@Path` value needs to be set as `@Path('{profileId}')` where 'profileId' would be our path param place holder and would be received at method arguments by using `@PathParam('profileId') <data type> <variable>`
  * we need to use same for `PathParam` which is used at `@Path`

### Http Get Implementation - Get Data
* Http Request should be having method type as GET
* this would be used to get information from server either in the form of xml or json as per the request
* Resource method would be annotated with `@GET` or `@GET("<path>")` and use `@Produces(MediaType.XXXXXXXXXX)` to return either xml or json
  * MediaType.APPLICATION_JSON
  * MediaType.APPLICATION_XML
  * MediaType.TEXT_PLAIN


### Http Post Implementation - Add New Data
* Http Request should be having method type as POST
* this would be used to add the data
* Resource method would be annotated with `@POST` or `@POST("<path>")` and would be received as simple text at method arguments
  * to receive in the form of object, use `@Consumes(MediaType.XXXXXXXXXX)` to consume the data and for method arguments use the object which is required (which is resource data object)

### Http Put Implementation - Update Data
* Http Request should be having method type as UPDATE
* this would be used to update data
* Resource method would be annotated with `@UPDATE` or `@UPDATE("<path>")` 
  * to receive in the form of object, use `@Consumes(MediaType.XXXXXXXXXX)` to consume the data and for method arguments use the object which is required (which is resource data object)


### Http Delete Implementation - Delete Data
* Http Request should be having method type as DELETE
* this would be used to update data
* Resource method would be annotated with `@DELETE` or `@DELETE("<path>")` 
  * Use `PathParam` to get Id
  * Use `@Consumes(MediaType.XXXXXXXXXX)` to consume the data and for method arguments use the object which is required (which is resource data object)


## Resource Data Object 
* Create a POJO and annotate with `@XmlRootElement`.  Whenever resource method returns this object and as per `Produces` and annotation arguments, either XML or JSON would be created.
* Must be implemented **public no argument constuctor** else conversion would fail






## References
* [Youtube Videos] (https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn)