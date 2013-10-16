package org.scoovy.utils;

import java.lang.reflect.Type;

import org.joda.time.DateTime;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateTImeDeserializer implements JsonDeserializer<DateTime>{
	@Override
	public DateTime deserialize(JsonElement element, Type type,
			JsonDeserializationContext context)throws JsonParseException {
		return new DateTime(element.getAsJsonPrimitive().getAsString());
	}
}
