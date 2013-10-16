package org.scoovy.positionmanager.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Point {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
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
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
