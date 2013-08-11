package org.scoovy.positionmanager.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.scoovy.positionmanager.dao.MemberDao;
import org.scoovy.positionmanager.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class MemberDaoJDBC implements MemberDao{
	private class MemberMapper implements RowMapper<Member>{
		@Override
		public Member mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			String educationNumber = resultSet.getString("educationNumber");
			String name = resultSet.getString("name");
			return new Member(educationNumber, name);
		}
		
	}
	@Autowired
	private JdbcTemplate jdbcTempate;
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	@Override
	public Member login(String educateeNumber, String password) {
		MapSqlParameterSource map = new MapSqlParameterSource()
			.addValue("EDUCATION_NUMBER", educateeNumber)
			.addValue("PASSWORD", password);
		try{
			return this.npJdbcTemplate.queryForObject(
				"SELECT * FROM MEMBER WHERE educationnumber = :EDUCATION_NUMBER AND PASSWORD = :PASSWORD", 
				map, 
				new MemberMapper()
			);			
		}catch(EmptyResultDataAccessException e){
			return null;
		}

	}

	@Override
	public void addMember(Member member, String password) {
		MapSqlParameterSource map = new MapSqlParameterSource()
			.addValue("NAME", member.getName())
			.addValue("EDUCATION_NUMBER", member.getEducationNumber())
			.addValue("PASSWORD", password);
		this.npJdbcTemplate.update(
			"INSERT INTO MEMBER(educationnumber, name, password) " +
							"values (:EDUCATION_NUMBER, :NAME, :PASSWORD)", map);
	}

	@Override
	public List<Member> list() {
		return this.jdbcTempate.query("SELECT * FROM MEMBER ORDER BY educationnumber", new MemberMapper());
	}
}
