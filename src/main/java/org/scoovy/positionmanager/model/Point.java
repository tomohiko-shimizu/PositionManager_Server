package org.scoovy.positionmanager.model;

import org.apache.commons.lang3.Validate;


public class Point {
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	private int x;
	private int y;
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public Point(int x, int y) {
		super();
		Validate.isTrue(x > 0);
		Validate.isTrue(y > 0);
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Point other = (Point) obj;
		if (this.x != other.x)
			return false;
		if (this.y != other.y)
			return false;
		return true;
	}
}
