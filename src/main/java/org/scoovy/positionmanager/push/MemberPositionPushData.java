package org.scoovy.positionmanager.push;

import java.util.List;

import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.Point;
import java.util.ArrayList;

public class MemberPositionPushData implements PushData {
	private String educationNumber;
	private List<Point> positions = new ArrayList<>();
	private Long roomId;

	public MemberPositionPushData(Member member, List<Point> points, long roomId) {
		this.educationNumber = member.getEducationNumber();
		this.positions = new ArrayList<>(points);
		this.roomId = roomId;
	}

	public String getEducationNumber() {
		return this.educationNumber;
	}

	public List<Point> getPositions() {
		return new ArrayList<>(this.positions);
	}

}
