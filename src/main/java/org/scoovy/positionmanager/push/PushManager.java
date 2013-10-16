package org.scoovy.positionmanager.push;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushManager {
	Logger logger = LoggerFactory.getLogger(PushManager.class);
	private List<PushContext> pushContexts = new CopyOnWriteArrayList<>();
	public void addContext(PushContext context) {
		this.pushContexts.add(context);
		this.logger.debug("add new Context : " + context);
		this.logger.debug("after added context count : " + this.pushContexts.size());
	}
	public void removeContext(PushContext context) {
		this.pushContexts.remove(context);
		this.logger.debug("remove Context : " + context);
		this.logger.debug("after removed context count : " + this.pushContexts.size());
	}
	public void notifyPushData(PushData data){
		for(PushContext context : this.pushContexts){
			this.logger.debug("push target : " + context.toString());
			this.logger.debug("push data :" + data);
			if(!context.isPush(data)) continue;
			try {
				context.push(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
