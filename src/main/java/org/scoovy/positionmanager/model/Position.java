package org.scoovy.positionmanager.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.Point;


public class Position{
	private Point point;
	private DateTime time;
	private Member member;
	private int roomId;
	public Position(Member member, int roomId, Point point, DateTime time){
		this.member = member;
		this.roomId = roomId;
		this.time = time;
	}
	public Point getPoint() {
		return this.point;
	}
	public Member getMember() {
		return member;
	}
	
}
