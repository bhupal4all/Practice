package com.ranga.webservices.resources.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationServerRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		final SecurityContext securityContext = requestContext
				.getSecurityContext();
		if ("POST".equals(requestContext.getMethod())
				&& (securityContext == null
				|| !securityContext.isUserInRole("privileged"))) {
			requestContext.abortWith(Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("User cannot access the resource.").build());
		}
	}

}
