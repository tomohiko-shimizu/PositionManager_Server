package org.scoovy.positionmanager.servlet;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.scoovy.positionmanager.map.PositionMapper;
import org.scoovy.positionmanager.push.PushManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class BootStrap
 *
 */
@WebListener
public class BootStrap implements ServletContextListener {
	public static final String POSITION_MAPPER ="org.scoovy.positionmanager.servlet.BootStrap.mapper";
	public static final String PUSH_MANAGER = "orgs.coovy.positionmaanager.serlet.BootStrap.pushManager";
	private static final Logger logger = LoggerFactory.getLogger(BootStrap.class); 
    public BootStrap() {
    }

    public void contextInitialized(ServletContextEvent event) {
    	
    	logger.info("init positionMapper start");
    	try {
			PositionMapper mapper = PositionMapper.load("/positions.csv");
			event.getServletContext().setAttribute(POSITION_MAPPER, mapper);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
    	logger.info("positionMapper initialized end");
    	PushManager manager = new PushManager();
    	event.getServletContext().setAttribute(PUSH_MANAGER, manager);
    }
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
