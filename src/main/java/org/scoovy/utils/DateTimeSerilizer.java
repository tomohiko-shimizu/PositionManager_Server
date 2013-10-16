package org.scoovy.utils;

import java.lang.reflect.Type;

import org.joda.time.DateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateTimeSerilizer implements JsonSerializer<DateTime>{
	@Override
	public JsonElement serialize(DateTime dateTime, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(dateTime.toString("yyyy-MM-dd'T'HH:mm:ss.SSS"));
	}
}
