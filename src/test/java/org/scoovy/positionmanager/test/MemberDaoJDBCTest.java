package org.scoovy.positionmanager.test;

import static org.junit.Assert.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.h2.util.JdbcUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.scoovy.positionmanager.dao.MemberDao;
import org.scoovy.positionmanager.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/testApplicationContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class MemberDaoJDBCTest{
	private IDatabaseTester tester;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private DataSource dataSource;
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
			"memebr_id LONG," +
			"FOREIGN KEY(memebr_id) REFERENCES Member(id))"
		);
		this.tester = new DataSourceDatabaseTester(this.dataSource);
		JdbcUtils.closeSilently(connection);
		IDataSet dataSet = new FlatXmlDataSet(super.getClass().getResource("/testdata/member.xml"));
		this.tester.setDataSet(dataSet);
		this.tester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		this.tester.onSetup();
	}
	@Test
	public void testAddMember() throws Exception{
		this.memberDao.addMember(new Member("20JK99", "ishi"), "dcl");
		ITable requiredTable = new FlatXmlDataSet(super.getClass()
				.getResource("/testdata/aftertestAddmember.xml")).getTable("MEMBER");
		ITable table = this.tester.getConnection().createDataSet().getTable("MEMBER");
		table = DefaultColumnFilter.excludedColumnsTable(table, new String[]{"id"});
		Assertion.assertEquals(table, requiredTable);
	}
	@Test 
	public void testList() throws Exception{
		List<Member> list = this.memberDao.list();
		ITable dataSet = new FlatXmlDataSet(super.getClass().getResource("/testdata/member.xml")).getTable("MEMBER");
		assertEquals(list.size(), dataSet.getRowCount());
	}
	@Test
	public void testLogin(){
		//正しい学籍番号と名前の場合
		Member member = this.memberDao.login("11JKM15", "dcldcl");
		assertNotNull(member);
		assertEquals("11JKM15", member.getEducationNumber());
		assertEquals("shimizu", member.getName());
		//パスワードが違う場合
		member = this.memberDao.login("11JKM15", "false");
		assertNull(member);
		
		//学籍番号が違う場合
		member = this.memberDao.login("11JKM111", "dcldcl");
		assertNull(member);
		
		//両方違う場合
		member = this.memberDao.login("11JKM111", "dcldcl");
		assertNull(member);
	}
	
	@After
	public void after() throws Exception{
		this.tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		this.tester.onTearDown();
	}
}