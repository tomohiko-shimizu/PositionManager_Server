package org.scoovy.positionmanager.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
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


public class MemberDaoJDBCTest extends AbstractPositionManagerJDBCTestCase{
	@Autowired
	MemberDao memberDao;
	public void testAddMember(){
		try{
			this.memberDao.addMember(new Member("20JK99", "ishi", Long.MAX_VALUE), "dcl");
			ITable requiredTable = new FlatXmlDataSet(super.getClass()
				.getResource("/testdata/aftertestAddmember.xml")).getTable("MEMBER");
			ITable table = super.tester.getConnection().createDataSet().getTable("MEMBER");
			table = DefaultColumnFilter.excludedColumnsTable(table, new String[]{"id"});
			Assertion.assertEquals(table, requiredTable);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testExists(){
		assertTrue(this.memberDao.exists("11JKM15"));
		assertFalse(this.memberDao.exists(">>>"));
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
		String correctId = "11JKM15";
		String correctPassword = "dcldcl";
		Member member = this.memberDao.login(correctId, correctPassword);
		assertNotNull(member);
		assertEquals(correctId, member.getEducationNumber());
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
}