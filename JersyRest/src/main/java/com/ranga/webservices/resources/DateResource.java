package com.ranga.webservices.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ranga.webservices.resources.data.MyDate;

@Path("/date")
public class DateResource {
	
	@GET
	@Path("/{dateFilter}")
	@Produces(MediaType.APPLICATION_JSON)
	public MyDate getDate(@PathParam("dateFilter") MyDate date) {
		return date;
	}
}
