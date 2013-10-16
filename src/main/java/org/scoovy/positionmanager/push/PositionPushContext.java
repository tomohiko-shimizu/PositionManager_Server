package org.scoovy.positionmanager.push;

import org.apache.catalina.websocket.StreamInbound;
import org.scoovy.positionmanager.model.input.PointsData;

public class PositionPushContext extends PushContext{
	private String educateeNumber;
	public PositionPushContext(String educateeNumber, StreamInbound inbound) {
		super(inbound);
		this.educateeNumber = educateeNumber;
	}
	@Override
	public boolean isPush(PushData pushData) {
		if(!(pushData instanceof PointsData)){
			return false;
		}
		PointsData positionPushData = (PointsData) pushData;
		return this.educateeNumber.equals(positionPushData.getEducationNumber());
	}
	
	@Override
	public void push(PushData pushData) {
	}
}
