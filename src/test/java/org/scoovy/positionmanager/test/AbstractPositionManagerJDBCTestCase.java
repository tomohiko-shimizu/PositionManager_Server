package org.scoovy.positionmanager.test;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.h2.util.JdbcUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/testApplicationContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class AbstractPositionManagerJDBCTestCase {
	protected IDatabaseTester tester;
	@Autowired
	protected DataSource dataSource;
	
	@Before
	public void init() throws Exception{
		Connection connection = null;
		connection =  this.dataSource.getConnection();
		Statement stmt = connection.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS Member(" +
			"id LONG PRIMARY KEY  AUTO_INCREMENT," +
			"educationNumber  VARCHAR(256)," +
			"name VARCHAR(256)," +
			"password VARCHAR(256))"
		);
		stmt.execute("CREATE TABLE IF NOT EXISTS Position(" +
			"x INTEGER," +
			"y INTEGER," +
			"room INTEGER," +
			"date TIMESTAMP," +
			"member_id LONG," +
			"FOREIGN KEY(member_id) REFERENCES Member(id))"
		);
		this.tester = new DataSourceDatabaseTester(this.dataSource);
		JdbcUtils.closeSilently(connection);
		IDataSet dataSet = new FlatXmlDataSet(super.getClass().getResource("/testdata/member.xml"));
		this.tester.setDataSet(dataSet);
		this.tester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		this.tester.onSetup();
	}
	@After
	public void after() throws Exception{
		this.tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		this.tester.onTearDown();
	}
}
