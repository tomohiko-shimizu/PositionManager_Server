package org.scoovy.positionmanager.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.scoovy.positionmanager.model.Point;

public class PointTestCase {
	private static final int X = 10;
	private static final int Y = 20;
	private Point testPoint = new Point(X, Y);
	@Test(expected=IllegalArgumentException.class)
	public void testConstructXUnderZero() {
		new Point(0, 1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testConstructYUnderZero() {
		new Point(1, 0);
	}
	@Test
	public void testConstruct() {
		new Point(X, Y);
	}
	@Test
	public void testGetX() {
		assertEquals(this.testPoint.getX(), X);
	}
	@Test
	public void testGetY() {
		assertEquals(this.testPoint.getY(), Y);
	}
	@Test
	public void testEquals(){
		assertEquals(this.testPoint, new Point(X, Y));
		assertTrue(!this.testPoint.equals(new Point(X + 1, Y)));
	}
	@Test
	public void testHashCode(){
		assertEquals(this.testPoint.hashCode(), new Point(X , Y).hashCode());
		assertTrue(!(this.testPoint.hashCode() == new Point(X , Y + 1).hashCode()));
	}
}
