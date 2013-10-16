package org.scoovy.positionmanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.scoovy.positionmanager.model.Member;
import org.scoovy.positionmanager.model.input.PointsData;
import org.scoovy.positionmanager.push.MemberPositionPushData;
import org.scoovy.positionmanager.push.PushData;
import org.scoovy.positionmanager.push.PushManager;
import org.scoovy.positionmanager.service.MemberManager;
import org.scoovy.positionmanager.service.PositionManager;
import org.scoovy.utils.DateTImeDeserializer;
import org.scoovy.utils.DateTimeSerilizer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class PositionUpdateServlet
 */
@WebServlet("/updatePosition")
public class PositionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositionUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		WebApplicationContext context = StaticApplicaitonContext.getBeanFactory(this.getServletContext());
		MemberManager memberManager =  context.getBean(MemberManager.class);
		PositionManager positionManager = context.getBean(PositionManager.class);
		Gson gson = StaticApplicaitonContext.buildGson();
		PointsData data = gson.fromJson(request.getReader(), PointsData.class);
		Member member = memberManager.findByEeucationNumber(data.getEducationNumber());
		
		PushManager manager = StaticApplicaitonContext.manager;
		manager.notifyPushData(data);
		System.out.println(positionManager);
		positionManager.insertPosition(member.getId(), data.getRoomId(), data.getPoints());
	}

}
