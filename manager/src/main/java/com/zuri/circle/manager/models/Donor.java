package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Donor")
public class Donor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private User user;
	private String email;
	private String totalAmount;
	private Map<String,String> donationStat = new HashMap<>();
	
	public Donor(String id, User user, String email, String totalAmount, Map<String, String> donationStat) {
		super();
		this.id = id;
		this.user = user;
		this.email = email;
		this.totalAmount = totalAmount;
		this.donationStat = donationStat;
	}
	
public Donor() {
	
}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Map<String, String> getDonationStat() {
		return donationStat;
	}
	public void setDonationStat(Map<String, String> donationStat) {
		this.donationStat = donationStat;
	}
}
