package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Volunteer")
public class Volunteer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private User user;
	private List<Events> listOfEvents;
	public Volunteer(String id, User user, List<Events> listOfEvents) {
		super();
		this.id = id;
		this.user = user;
		this.listOfEvents = listOfEvents;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Events> getListOfEvents() {
		return listOfEvents;
	}
	public void setListOfEvents(List<Events> listOfEvents) {
		this.listOfEvents = listOfEvents;
	}

}
