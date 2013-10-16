package org.scoovy.positionmanager.test;


import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.scoovy.positionmanager.model.Point;
import org.scoovy.positionmanager.model.input.PointsData;
import org.scoovy.utils.DateTImeDeserializer;
import org.scoovy.utils.DateTimeSerilizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PushFataGsonTest {

	@Test
	public void testPositionPushData(){
		GsonBuilder builder = new GsonBuilder()
			.registerTypeAdapter(DateTime.class, new DateTimeSerilizer())
			.registerTypeAdapter(DateTime.class, new DateTImeDeserializer());
		Gson gson = builder.create();
		List<Point> points = Arrays.asList(
			new Point(1, 1),
			new Point(1, 2),
			new Point(1, 3)
		);
		PointsData pointData = new PointsData(points, "11JKM245", 301L);
		String json = gson.toJson(pointData);
		System.out.println(json);
		PointsData data = gson.fromJson(json, PointsData.class);
		System.out.println(data);
	}

}
