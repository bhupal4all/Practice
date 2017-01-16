package com.ranga.webservices.resources.messagebody.reader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.TEXT_PLAIN)
public class DateMessageBodyReader implements MessageBodyReader<Date> {

	@Override
	public boolean isReadable(Class<?> type, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		return Date.class.isAssignableFrom(type);
	}

	@Override
	public Date readFrom(Class<Date> aClass, Type arg1, Annotation[] arg2,
			MediaType arg3, MultivaluedMap<String, String> arg4, InputStream in)
			throws IOException, WebApplicationException {

		StringBuilder dateStr = new StringBuilder();
		int i;
		char c;
		while ((i = in.read()) != -1) {
			c = (char) i;
			dateStr.append(c);
		}
		System.out.println(dateStr);

		return Calendar.getInstance().getTime();
	}

}
