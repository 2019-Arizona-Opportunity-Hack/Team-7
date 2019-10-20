package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Events")
public class Events implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String eventId;
	private String location;
	private String name;
	private String startTime;
	private String endTime;
	private String eventDate;
	private List<Volunteer> volunteers = new ArrayList<>();

	public Events(String eventId, String location, String name, String startTime, String endTime, String eventDate) {
		super();
		this.eventId = eventId;
		this.location = location;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.eventDate = eventDate;
		
	}

	public Events() {}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public List<Volunteer> getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}

}
