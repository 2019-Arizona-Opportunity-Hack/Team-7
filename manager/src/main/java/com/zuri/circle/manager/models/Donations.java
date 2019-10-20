package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

public class Donations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String donationId;
	private String donerId;
	private String amount;
	@CreatedDate
	private Date donationDate;
	public Donations(String donationId, String donarId, String amount, Date donationDate) {
		super();
		this.donationId = donationId;
		this.donerId = donarId;
		this.amount = amount;
		this.donationDate = donationDate;
	}
	public Donations() {}
	public String getDonationId() {
		return donationId;
	}
	public void setDonationId(String donationId) {
		this.donationId = donationId;
	}
	public String getDonerId() {
		return donerId;
	}
	public void setDonerId(String donerId) {
		this.donerId = donerId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getDonationDate() {
		return donationDate;
	}
	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

}
