package org.scoovy.positionmanager.service;

import java.util.List;

import org.scoovy.positionmanager.dao.MemberDao;
import org.scoovy.positionmanager.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class MemberManager {
	@Autowired
	private MemberDao memberDao;
	@Transactional(
		noRollbackFor=Exception.class,
		readOnly=true
	)
	public List<Member> list(){
		return this.memberDao.list();
	}
	public  Member findByEeucationNumber(String educationNumber){
		return this.memberDao.findByEducationNumber(educationNumber);
	}
}
