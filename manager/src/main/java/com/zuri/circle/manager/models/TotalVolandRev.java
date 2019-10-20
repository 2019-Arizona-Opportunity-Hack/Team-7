package com.zuri.circle.manager.models;

import java.io.Serializable;

public class TotalVolandRev implements Serializable {
	
	private String totalVolunteers;
	private  String totalRevenue;
	public TotalVolandRev(String totalVolunteers, String totalRevenue) {
		super();
		this.totalVolunteers = totalVolunteers;
		this.totalRevenue = totalRevenue;
	}
	
	public TotalVolandRev() {
		
	}
	public String getTotalVolunteers() {
		return totalVolunteers;
	}
	public void setTotalVolunteers(String totalVolunteers) {
		this.totalVolunteers = totalVolunteers;
	}
	public String getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
	

}
