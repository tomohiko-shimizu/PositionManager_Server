package org.scoovy.positionmanager.test;

import org.junit.Test;
import org.scoovy.positionmanager.model.Point;
import org.scoovy.positionmanager.model.Member;
import static org.junit.Assert.*;
public class UserTestCase{
	private static final String USER_NAME = "tomohiko";
	private static final String EDUCATION_ID = "11JKM15";
	
	private Member testUser = new Member(EDUCATION_ID, USER_NAME);
	public UserTestCase() {
	}
	@Test(expected=NullPointerException.class)
	public void testConstEducateeNumberNull(){
		new Member(null, "tomohiko");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testConstEducateeNumberEmpty(){
		new Member("", "tomohiko");
	}
	@Test(expected=NullPointerException.class)
	public void testConstEducatNameNull(){
		new Member("11JKM15", null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testConstNameEmpty(){
		new Member("11JKM15", "");
	}
	@Test
	public void testGetName(){
		assertEquals(this.testUser.getName(), USER_NAME); 
	}
	@Test
	public void testGeteducationNumber(){
		assertEquals(testUser.getEducationNumber(), EDUCATION_ID);
	}
	@Test
	public void testEquals(){
		String falseID = "11JKM14";
		String falseNmae = "tomohik"; 
		Member trueTestUser = new Member(EDUCATION_ID, USER_NAME);
		Member falseTestUser = new Member(falseID, falseNmae);
		assertEquals(this.testUser, trueTestUser);
		assertFalse(falseTestUser.equals(this.testUser));
	}
	@Test
	public void testHashCode(){
		String falseID = "11JKM16";
		String falseNmae = "tomohikoo";
		assertEquals(this.testUser.hashCode(), new Member(EDUCATION_ID, USER_NAME).hashCode());
		assertTrue(!(this.testUser.hashCode() == new Member(falseID, falseNmae).hashCode()));
	}
}
