package com.zuri.circle.manager.Request;

import java.io.Serializable;
import java.util.Date;

public class DonationReport implements Serializable{

	private static final long serialVersionUID = 1L;
	private String donorName;
	private String amount;
	private String donationId;
	private Date donationDate;
	
	public DonationReport() {}

	public DonationReport(String amount,String donorName, Date donationDate) {
		super();
		this.donorName = donorName;
		this.amount = amount;
		this.donationId = donationId;
		this.donationDate = donationDate;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDonationId() {
		return donationId;
	}

	public void setDonationId(String donationId) {
		this.donationId = donationId;
	}

	public Date getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}
	
}
