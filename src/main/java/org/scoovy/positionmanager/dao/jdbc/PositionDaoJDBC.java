package org.scoovy.positionmanager.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.scoovy.positionmanager.dao.PositionDao;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.Point;
import org.scoovy.positionmanager.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class PositionDaoJDBC implements PositionDao{
	private static class PointMapper implements RowMapper<Position>{
		private Member member;
		public PointMapper(Member member) {
			this.member = member;
		}
		@Override
		public Position mapRow(ResultSet set, int number) throws SQLException {
			Integer x = set.getInt("x");
			Integer y = set.getInt("y");
			Point point = new Point(x, y);
			DateTime time = new DateTime(set.getDate("date"));
			int roomId = set.getInt("room");
			return new Position(this.member, roomId, point, time);
		}
	}
	@Autowired
	private JdbcTemplate jdbcTempate;
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	private void addPoint(Long memberId, Long roomId, Point point, Timestamp timestmp){
		MapSqlParameterSource map = new MapSqlParameterSource()
			.addValue("x", point.getX())
			.addValue("y", point.getY())
			.addValue("roomId", roomId)
			.addValue("memberId", memberId)
			.addValue("date", timestmp);
		this.npJdbcTemplate.update(
				"INSERT INTO Position(x, y, room, member_id, date) " +
						"values(:x, :y, :roomId, :memberId, :date)", map);
	}
	@Override
	public void addPosition(Long memberId, Long roomId, List<Point> points) {
		Timestamp timestmp = new Timestamp(new DateTime().getMillis());
		for(Point point : points){
			this.addPoint(memberId, roomId, point, timestmp);
		}
	}

	@Override
	public List<Position> getPoints(Member member, Interval interval) {
		Timestamp begin = new Timestamp(interval.getStart().getMillis());
		Timestamp end = new Timestamp(interval.getEnd().getMillis());
		MapSqlParameterSource map  = new MapSqlParameterSource()
			.addValue("memberId", member.getId())
			.addValue("begin", begin)
			.addValue("end", end);
		return this.npJdbcTemplate.query(
			"SELECT * FROM position " +
			 "where member_id=:memberId " +
			 "and date between :begin and :end", 
			map, new PointMapper(member)
		);
	}
	
}
