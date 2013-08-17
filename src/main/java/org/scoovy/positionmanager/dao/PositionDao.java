package org.scoovy.positionmanager.dao;

import java.util.List;

import org.joda.time.Interval;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.Point;
import org.scoovy.positionmanager.model.Position;

public interface PositionDao{
	public List<Position> getPoints(Member member, Interval interval);
	public void addPosition(Long memberId, Long roomId, List<Point> points);
}
