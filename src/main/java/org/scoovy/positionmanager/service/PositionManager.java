package org.scoovy.positionmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;
import org.scoovy.positionmanager.dao.MemberDao;
import org.scoovy.positionmanager.dao.PositionDao;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.Point;
import org.scoovy.positionmanager.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PositionManager {
	@Autowired
	private PositionDao positionDao;
	@Autowired
	private MemberDao memberDao;
	@Transactional(
		propagation=Propagation.REQUIRED,
		isolation=Isolation.READ_COMMITTED, 
		noRollbackFor=Exception.class,
		timeout=10,
		readOnly=false
	)
	public boolean insertPosition(Long memberId, Long roomId, List<Point> points){
		if(!this.memberDao.exists(memberId)) return false;
		this.positionDao.addPosition(memberId, roomId, points);
		return true;
	}
	@Transactional(
		propagation=Propagation.REQUIRED,
		isolation=Isolation.READ_COMMITTED, 
		noRollbackFor=Exception.class,
		timeout=10,
		readOnly=true
	)
	public List<Position> readPositions(Long memberId, Interval interval){
		Member member = this.memberDao.findByID(memberId);
		if(member == null) return new ArrayList<>();
		return this.positionDao.getPoints(member, interval);
	}
}
