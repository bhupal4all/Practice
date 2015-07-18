package com.ranga.webservices.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.ArrayList;

import com.ranga.webservices.resources.data.Message;

@Path("/messages")
public class Messages {
	List<Message> list = new ArrayList<Message>();

	{
		Message one = new Message(1,"One");
		Message two = new Message(2,"Two");
		Message thr = new Message(3,"Three");
		
		list.add(one);
		list.add(two);
		list.add(thr);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessages() {
		return "Getting Messages "+(new Message(1,"One")).toString();
	}

	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessagesXml() {
		return list;
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON )
	public List<Message> getMessagesJson() {
		return list;
	}

	@POST
	@Path("/json/add")
	public boolean addMessageJson(Message message) {
		list.add(message);
		return true;
	}

}
