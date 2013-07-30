package org.scoovy.positionmanager.test;

import org.junit.Test;
import org.scoovy.positionmanager.model.Point;
import org.scoovy.positionmanager.model.User;
import static org.junit.Assert.*;
public class UserTestCase{
	private static final String USER_NAME = "tomohiko";
	private static final String EDUCATION_ID = "11JKM15";
	
	private User testUser = new User(EDUCATION_ID, USER_NAME);
	public UserTestCase() {
	}
	@Test(expected=NullPointerException.class)
	public void testConstEducateeNumberNull(){
		new User(null, "tomohiko");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testConstEducateeNumberEmpty(){
		new User("", "tomohiko");
	}
	@Test(expected=NullPointerException.class)
	public void testConstEducatNameNull(){
		new User("11JKM15", null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testConstNameEmpty(){
		new User("11JKM15", "");
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
		User trueTestUser = new User(EDUCATION_ID, USER_NAME);
		User falseTestUser = new User(falseID, falseNmae);
		assertEquals(this.testUser, trueTestUser);
		assertFalse(falseTestUser.equals(this.testUser));
	}
	@Test
	public void testHashCode(){
		String falseID = "11JKM16";
		String falseNmae = "tomohikoo";
		assertEquals(this.testUser.hashCode(), new User(EDUCATION_ID, USER_NAME).hashCode());
		assertTrue(!(this.testUser.hashCode() == new User(falseID, falseNmae).hashCode()));
	}
}
