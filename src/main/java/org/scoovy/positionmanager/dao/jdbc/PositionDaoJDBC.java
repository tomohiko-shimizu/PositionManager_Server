package org.scoovy.positionmanager.dao.jdbc;

import java.util.List;

import org.joda.time.Interval;
import org.scoovy.positionmanager.dao.PositionDao;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class PositionDaoJDBC implements PositionDao{
	
	@Autowired
	private JdbcTemplate jdbcTempate;
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	private void addPoint(Long memberId, Long roomId, Point point){
		MapSqlParameterSource map = new MapSqlParameterSource()
			.addValue("x", point.getX())
			.addValue("y", point.getY())
			.addValue("roomId", roomId)
			.addValue("memberId", memberId);
		this.npJdbcTemplate.update(
				"INSERT INTO Position(x, y, room, member_id) " +
						"values(:x, :y, :roomId, :memberId)", map);
	}
	@Override
	public void addPosition(Long memberId, Long roomId, List<Point> points) {
		for(Point point : points){
			this.addPoint(memberId, roomId, point);
		}
	}

	@Override
	public List<Point> getPoints(Member member, Interval interval) {
		return null;
	}
	
}
