package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.List;

public class DashBoard implements Serializable {

	private String totalEvents;
	private List<Events> events ;
	public DashBoard(String totalEvents, List<Events> events) {
		super();
		this.totalEvents = totalEvents;
		this.events = events;
	}
	public DashBoard() {
		
	}
	public String getTotalEvents() {
		return totalEvents;
	}
	public void setTotalEvents(String totalEvents) {
		this.totalEvents = totalEvents;
	}
	public List<Events> getEvents() {
		return events;
	}
	public void setEvents(List<Events> events) {
		this.events = events;
	}
	
	
	
	
}
