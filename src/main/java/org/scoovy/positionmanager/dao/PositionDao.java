package org.scoovy.positionmanager.dao;

import java.util.List;

import org.joda.time.Interval;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.Point;

public interface PositionDao{
	public List<Point> getPoints(Member member, Interval interval);
	public void addPosition(Long memberId, Long roomId, List<Point> points);
}
