package com.zuri.circle.manager.Request;

import java.io.Serializable;

public class DonationReport implements Serializable{

	private static final long serialVersionUID = 1L;
	private String donorName;
	private String amount;
	private String donationId;
	public DonationReport(String donorName, String amount, String donationId) {
		super();
		this.donorName = donorName;
		this.amount = amount;
		this.donationId = donationId;
	}
	public DonationReport() {}
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
}
