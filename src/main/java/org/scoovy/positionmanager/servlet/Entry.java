package org.scoovy.positionmanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scoovy.positionmanager.dao.MemberDao;
import org.scoovy.positionmanager.map.PositionMapper;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.service.MemberManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * Servlet implementation class Entry
 */
@WebServlet("/entry")
public class Entry extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    public Entry() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext wac = WebApplicationContextUtils
		        .getRequiredWebApplicationContext(this.getServletContext());
		MemberManager manager = wac.getBean(MemberManager.class);
		request.setAttribute("members", manager.list());
		request.getRequestDispatcher("/members.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
