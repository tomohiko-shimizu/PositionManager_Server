package org.scoovy.positionmanager.test;

import java.util.Arrays;

import org.junit.Test;
import org.scoovy.positionmanager.dao.PositionDao;
import org.scoovy.positionmanager.model.Point;
import org.springframework.beans.factory.annotation.Autowired;

public class PostionDaoJDBCTest extends AbstractPositionManagerJDBCTestCase{
	@Autowired
	PositionDao positionDao;
	@Test
	public void testAddPosition(){
		this.positionDao.addPosition(1L, 301L, Arrays.asList(new Point(1,1)));
	}
}
