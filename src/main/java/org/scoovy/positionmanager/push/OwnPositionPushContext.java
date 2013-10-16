package org.scoovy.positionmanager.push;

import java.io.IOException;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.StreamInbound;
import org.scoovy.positionmanager.model.input.PointsData;

import com.google.gson.Gson;

public class OwnPositionPushContext extends PushContext{
	private String educationNumber;
	public OwnPositionPushContext(StreamInbound inbound, String educationNumber) {
		super(inbound);
		this.educationNumber = educationNumber;
	}
	@Override
	public boolean isPush(PushData pushData) {
		if(!(pushData instanceof PointsData)){
			return false;
		}
		PointsData memberPositionPushData = (PointsData) pushData;
		return memberPositionPushData.getEducationNumber().equalsIgnoreCase(this.educationNumber);
	}
	public PointsData cast(PushData pushData){
		return (PointsData) pushData;
	}
	public void push(PushData pushData) throws IOException{
		if(!this.isPush(pushData)) return;
		PointsData memberPositionPushData = cast(pushData);
		Gson gson = new Gson();
		String json = gson.toJson(memberPositionPushData);
		super.inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(json));
	};
}
