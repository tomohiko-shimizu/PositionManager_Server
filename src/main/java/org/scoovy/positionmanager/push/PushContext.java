package org.scoovy.positionmanager.push;

import java.io.IOException;

import org.apache.catalina.websocket.StreamInbound;

public abstract class PushContext{
	protected StreamInbound inbound;
	public boolean isClosed(){
		return false;
	}
	public abstract boolean isPush(PushData pushData);
	public abstract void push(PushData pushData) throws IOException;
	public PushContext(StreamInbound inbound){
		this.inbound = inbound;
	}
}
