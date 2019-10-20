package com.zuri.circle.manager.models;

import java.io.Serializable;

public class CheckInEvent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String eventId;
	private String email;
	public CheckInEvent(String eventId, String volunteerId) {
		super();
		this.eventId = eventId;
		this.email = volunteerId;
	}
	
	public CheckInEvent() {
		
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	

}
