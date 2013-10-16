package org.scoovy.positionmanager.test;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Test;
import org.scoovy.positionmanager.dao.PositionDao;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.Point;
import org.scoovy.positionmanager.model.Position;
import org.springframework.beans.factory.annotation.Autowired;

public class PostionDaoJDBCTest extends AbstractPositionManagerJDBCTestCase{
	@Autowired
	PositionDao positionDao;
	@Test
	public void testAddPosition() throws Exception{
		this.positionDao.addPosition(1L, 301L, Arrays.asList(new Point(1000,2000)));
		ITable requiredTable = new FlatXmlDataSet(super.getClass()
				.getResource("/testdata/afterAddPosition.xml")).getTable("POSITION");
		requiredTable = DefaultColumnFilter.excludedColumnsTable(requiredTable, new String[]{"date"});
		ITable currentTable = super.tester.getConnection().createDataSet().getTable("POSITION");
		currentTable = DefaultColumnFilter.excludedColumnsTable(currentTable, new String[]{"date"});
		Assertion.assertEquals(requiredTable, currentTable);
	}
	@Test
	public void testGetPositions(){
		Member member = new Member("11JKM15", "shimizu", 1L);
		DateTime start = new DateTime(2013, 8, 14, 10, 10, 0);
		DateTime end = new DateTime(2013, 8, 14, 10, 20, 0);
		List<Position> positions = this.positionDao.getPoints(member, new Interval(start, end));
		System.out.println(positions);
		assertEquals(positions.size(), 3);
	}
}
