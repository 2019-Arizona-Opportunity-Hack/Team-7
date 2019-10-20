package com.zuri.circle.manager.Request;

import java.io.Serializable;

public class DonationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String donor;
	private String amount;
	public DonationRequest(String donor, String amount) {
		super();
		this.donor = donor;
		this.amount = amount;
	}
	public DonationRequest() {}
	public String getDonor() {
		return donor;
	}
	public void setDonor(String donor) {
		this.donor = donor;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
