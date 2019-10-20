package com.zuri.circle.manager.models;

import java.io.Serializable;

public class CheckInEvent implements Serializable{
	
	private String eventId;
	private String volunteerId;
	public CheckInEvent(String eventId, String volunteerId) {
		super();
		this.eventId = eventId;
		this.volunteerId = volunteerId;
	}
	
	public CheckInEvent() {
		
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}
	
	

}
