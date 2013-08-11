package org.scoovy.positionmanager.dao;

import java.util.List;

import org.scoovy.positionmanager.model.Member;
public interface MemberDao {
	public Member login(String educateeNumber, String password);
	public void addMember(Member member, String password);
	public List<Member> list();
}
