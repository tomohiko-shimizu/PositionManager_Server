package org.scoovy.positionmanager.test;



import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.scoovy.positionmanager.model.Point;
import org.scoovy.positionmanager.model.Position;
import org.scoovy.positionmanager.model.Member;

public class PositionTestCase {
	private Member testUser = new Member("11JKM15", "shimizu");
	private Point[] points = {new Point(1, 1), new Point(1,2)};
	@Test
	public void testConstruct() {
		new Position(new DateTime(), this.testUser);
	}
	@Test(expected=NullPointerException.class)
	public void testConstructUserNull() {
		new Position(new DateTime(), null, this.points);
	}
	@Test(expected=NullPointerException.class)
	public void testConstructDateTimeNull() {
		new Position(null, testUser, this.points);
	}
	@Test
	public void testGetPoinst(){
		Position position = new Position(new DateTime(), this.testUser, this.points);
		Set<Point> points1 = position.getPoints();
		Set<Point> points2 = position.getPoints();
		assertNotSame(points1, points2);
		assertTrue(points1.equals(points2));
	}
}
