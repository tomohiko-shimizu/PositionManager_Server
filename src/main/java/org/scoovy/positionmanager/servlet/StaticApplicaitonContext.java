package org.scoovy.positionmanager.servlet;

import javax.servlet.ServletContext;

import org.joda.time.DateTime;
import org.scoovy.positionmanager.push.PushManager;
import org.scoovy.utils.DateTImeDeserializer;
import org.scoovy.utils.DateTimeSerilizer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StaticApplicaitonContext {
	public static final PushManager manager = new PushManager();
	public static Gson buildGson(){
		return new GsonBuilder()
			.registerTypeAdapter(DateTime.class, new DateTimeSerilizer())
			.registerTypeAdapter(DateTime.class, new DateTImeDeserializer())
			.create();
	}
	public static WebApplicationContext getBeanFactory(ServletContext context){
		return WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	}
}
