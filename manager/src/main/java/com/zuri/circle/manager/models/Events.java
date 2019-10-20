package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Events")
public class Events implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String eventId;
	private String location;
	private String name;
	private String duration;
	private Date eventDate;
	private List<Volunteer> volunteers;
	
	public Events() {}

	public Events(String eventId, String location, String name, String duration, Date eventDate,
			List<Volunteer> volunteers) {
		super();
		this.eventId = eventId;
		this.location = location;
		this.name = name;
		this.duration = duration;
		this.eventDate = eventDate;
		this.volunteers = volunteers;
	}


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


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public Date getEventDate() {
		return eventDate;
	}


	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}


	public List<Volunteer> getVolunteers() {
		return volunteers;
	}


	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}

}
