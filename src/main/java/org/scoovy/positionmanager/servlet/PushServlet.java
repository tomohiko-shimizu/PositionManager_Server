package org.scoovy.positionmanager.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.joda.time.DateTime;
import org.scoovy.positionmanager.model.input.PointsData;
import org.scoovy.positionmanager.push.OwnPositionPushContext;
import org.scoovy.positionmanager.push.PushContext;
import org.scoovy.positionmanager.push.PushManager;
import org.scoovy.utils.DateTImeDeserializer;
import org.scoovy.utils.DateTimeSerilizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class PushServlet
 */
@WebServlet("/push")
public class PushServlet extends WebSocketServlet{
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(PushServlet.class);
    public PushServlet() {
        super();
    }
    
	@Override
	protected StreamInbound createWebSocketInbound(final String message,
			final HttpServletRequest request) {
		final String id = request.getParameter("id");
		final PushManager manager = StaticApplicaitonContext.manager;
		StreamInbound stream = new StreamInbound() {
			private OwnPositionPushContext context;
			@Override
			protected void onOpen(WsOutbound outbound) {
				super.onOpen(outbound);
				PushServlet.this.logger.info("connected webSocket : " + id);
				OwnPositionPushContext context = new OwnPositionPushContext(this, id);
				manager.addContext(context);
				this.context = context;
			}
			@Override
			protected void onClose(int status) {
				System.out.println(status);
				super.onClose(status);
				PushServlet.this.logger.info("closed webSocket : " + id);
				manager.removeContext(this.context);
			}
			@Override
			protected void onTextData(Reader reader) throws IOException {
				PushServlet.this.logger.info("recieved data");
				Gson gson = new GsonBuilder()
					.registerTypeAdapter(DateTime.class, new DateTimeSerilizer())
					.registerTypeAdapter(DateTime.class, new DateTImeDeserializer())
					.create();
				PointsData data = gson.fromJson(reader, PointsData.class);
				PushServlet.this.logger.debug(data.toString());
				manager.notifyPushData(data);
			}
			
			@Override
			protected void onBinaryData(InputStream in) throws IOException {
			}
		};
		return stream;
	}

}
