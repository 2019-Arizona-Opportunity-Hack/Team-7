package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Donations")
public class Donations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String donationId;
	private Donor donor;
	private String amount;
	@CreatedDate
	private Date donationDate;
	
	public Donations(String donationId, Donor donor, String amount) {
		super();
		this.donationId = donationId;
		this.donor = donor;
		this.amount = amount;
		this.donationDate = new Date();
	}

	public Donations() {}

	public String getDonationId() {
		return donationId;
	}

	public void setDonationId(String donationId) {
		this.donationId = donationId;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
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
