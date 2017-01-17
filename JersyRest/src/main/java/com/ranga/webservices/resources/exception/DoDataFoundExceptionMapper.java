package com.ranga.webservices.resources.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ranga.webservices.resources.exception.data.ErrorMessage;

@Provider	
public class DoDataFoundExceptionMapper implements
		ExceptionMapper<NoDataFoundException> {

	@Override
	public Response toResponse(NoDataFoundException excp) {
		ErrorMessage message = new ErrorMessage(404, excp.getMessage(),
				"http://com.ranga.webservices/");

		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

}
