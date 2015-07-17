package com.ranga.webservices.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.ArrayList;

import com.ranga.webservices.resources.data.Message;

@Path("/messages")
public class Messages {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessages() {
		return "Getting Messages "+(new Message(1,"One")).toString();
	}

	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessagesXml() {
		Message one = new Message(1,"One");
		Message two = new Message(1,"Two");
		Message thr = new Message(1,"Three");
		
		List<Message> list = new ArrayList<Message>();
		list.add(one);
		list.add(two);
		list.add(thr);
		return list;
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON )
	public List<Message> getMessagesJson() {
		Message one = new Message(1,"One");
		Message two = new Message(1,"Two");
		Message thr = new Message(1,"Three");
		
		List<Message> list = new ArrayList<Message>();
		list.add(one);
		list.add(two);
		list.add(thr);
		return list;
	}
}
