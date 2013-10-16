package org.scoovy.positionmanager.model.input;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.scoovy.positionmanager.model.Point;
import org.scoovy.positionmanager.push.PushData;

public class PointsData implements PushData{
	private List<Point> points;
	private String educationNumber;
	private Long roomId;
	public PointsData() {}
	public PointsData(List<Point> points, String educationNumber, Long roomId){
		this.points = new ArrayList<>(points);
		this.educationNumber = educationNumber;
		this.roomId = roomId;
	}
	public String getEducationNumber() {
		return this.educationNumber;
	}
	public List<Point> getPoints() {
		return new ArrayList<>(this.points);
	}
	public Long getRoomId() {
		return this.roomId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
