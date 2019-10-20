package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "NeedHelp")
public class NeedHelp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	  private String id;
      private User user;
      private List<String> helpType;
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
	public List<String> getHelpType() {
		return helpType;
	}
	public void setHelpType(List<String> helpType) {
		this.helpType = helpType;
	}
	public NeedHelp(String id, User user) {
		super();
		this.id = id;
		this.user = user;
	}
      
	public NeedHelp() {
		
	}
      

}
