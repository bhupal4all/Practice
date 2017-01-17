package com.ranga.webservices.resources.filter;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientNameClientRequestFilter implements ClientRequestFilter {
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		System.out.println("client filter executed = "
				+ requestContext.getHeaders().get("Client-Name"));
		if (requestContext.getHeaders().get("Client-Name").isEmpty()) {
			requestContext.abortWith(Response.status(Status.BAD_REQUEST)
					.entity("Client-Name header must be defined").build());
		}
	}

}
