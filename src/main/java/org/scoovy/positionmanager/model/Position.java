package org.scoovy.positionmanager.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.Point;


public class Position{
	private Point point;
	private DateTime time;
	private Member member;
	private int roomId;
	public Position(){}
	public Position(Member member, int roomId, Point point, DateTime time){
		this.member = member;
		this.roomId = roomId;
		this.time = time;
		this.point = point;
	}
	public Point getPoint() {
		return this.point;
	}
	public Member getMember() {
		return this.member;
	}
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.
				reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}
}
