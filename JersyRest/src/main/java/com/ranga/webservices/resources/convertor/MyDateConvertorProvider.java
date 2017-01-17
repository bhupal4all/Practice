package com.ranga.webservices.resources.convertor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import com.ranga.webservices.resources.data.MyDate;

@Provider
public class MyDateConvertorProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(final Class<T> rawType,
			Type genericType, Annotation[] annotations) {
		return new ParamConverter<T>() {

			@Override
			public T fromString(String value) {
				Calendar requestedDate = Calendar.getInstance();

				if (value.equals("tomorrow")) {
					requestedDate.add(Calendar.DATE, 1);
				} else if (value.equals("yesterday")) {
					requestedDate.add(Calendar.DATE, -1);
				}

				MyDate date = new MyDate(requestedDate.get(Calendar.YEAR),
						requestedDate.get(Calendar.MONTH),
						requestedDate.get(Calendar.DATE));

				return rawType.cast(date);
			}

			@Override
			public String toString(T obj) {
				if (obj != null)
					return obj.toString();

				return null;
			}

		};
	}

}
