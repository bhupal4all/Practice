package com.ranga.webservices.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ranga.webservices.resources.data.Message;

@Path("/")
public class MessagesResource {
	static List<Message> list = new ArrayList<Message>();
	static Map<Long, List<Message>> messageMap = new HashMap<Long, List<Message>>();

	static {
		Message one = new Message(1, "One");
		Message two = new Message(2, "Two");
		Message thr = new Message(3, "Three");

		list.add(one);
		list.add(two);
		list.add(thr);
		
		messageMap.put(1L, list);
	}

	// @GET
	// @Produces(MediaType.TEXT_PLAIN)
	// public String getMessages() {
	// return "Getting Messages "+(new Message(1,"One")).toString();
	// }
	//
	// @GET
	// @Path("/xml")
	// @Produces(MediaType.APPLICATION_XML)
	// public List<Message> getMessagesXml() {
	// return list;
	// }
	//
	// @GET
	// @Path("/json")
	// @Produces(MediaType.APPLICATION_JSON )
	// public List<Message> getMessagesJson() {
	// System.out.println(list);
	// return list;
	// }
	//
	// @POST
	// @Path("/json/add")
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	// public Message addMessageJson(Message messageObj) {
	// list.add(messageObj);
	// System.out.println(list);
	// return messageObj;
	// }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@PathParam("profileId") String profileId) {
		System.out.println("Profile Id " + profileId);
		
		return  messageMap.get(Long.parseLong(profileId));
	}

	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessageById(@PathParam("profileId") String profileId,
			@PathParam("messageId") String messageId) {
		System.out.println("profile id " + profileId);
		System.out.println("message id " + messageId);
		
		List<Message> list = messageMap.get(Long.parseLong(profileId));
		
		Message msg = null;

		for (int i=0; i<list.size();i++){
			if (list.get(i).getId() == Integer.parseInt(messageId)) {
				msg = list.get(i);
				break;
			}
		}

		return msg;
	}

}
