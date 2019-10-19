package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Donar")
public class Donor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private User user;
	private String totalAmount;
	private Map<Donations,String> donationStat = new HashMap<>();
	public Donor(String donarId, User user, String totalAmount, Map<Donations, String> donationStat) {
		super();
		this.id = donarId;
		this.user = user;
		this.totalAmount = totalAmount;
		this.donationStat = donationStat;
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
	public Map<Donations, String> getDonationStat() {
		return donationStat;
	}
	public void setDonationStat(Map<Donations, String> donationStat) {
		this.donationStat = donationStat;
	}
}
