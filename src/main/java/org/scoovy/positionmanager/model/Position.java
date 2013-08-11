package org.scoovy.positionmanager.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;

public class Position implements Iterable<Point>{
	private DateTime dateTime;
	private Set<Point> points;
	private Member user;
	public Position(DateTime dateTime, Member user, Point... points){
		Validate.notNull(dateTime, "dateTime is null");
		Validate.notNull(user, "user is null");
		Validate.notNull(points, "points is null");
		this.dateTime = new DateTime();
		this.user = user;
		this.points = new HashSet<Point>(Arrays.asList(points));
	}
	public DateTime getDateTime() {
		return this.dateTime;
	}
	public Set<Point> getPoints() {
		return new HashSet<Point>(points);
	}
	public Member getUser() {
		return this.user;
	}
	@Override
	public Iterator<Point> iterator() {
		return this.points.iterator();
	}
	@Override
	public String toString() {
		return "Position [dateTime=" + dateTime + ", points=" + points
				+ ", user=" + user + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
