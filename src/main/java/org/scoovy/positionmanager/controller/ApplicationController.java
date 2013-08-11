package org.scoovy.positionmanager.controller;
import org.scoovy.positionmanager.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class ApplicationController{
	@Autowired
	public MemberDao dao;
	@RequestMapping(value="home")	
	public String index(){
		System.out.println(dao.getClass());
		dao.list();
		System.out.println("access index");
		return "index";
	}
}
