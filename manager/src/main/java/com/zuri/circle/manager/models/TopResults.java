package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.List;

public class TopResults implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Donations> topDonations;
	private List<Donor> topDonors;

	public TopResults(List<Donations> topDonations, List<Donor> topDonors) {
		super();
		this.topDonations = topDonations;
		this.topDonors = topDonors;
	}

	public TopResults() {

	}

	public List<Donations> getTopDonations() {
		return topDonations;
	}

	public void setTopDonations(List<Donations> topDonations) {
		this.topDonations = topDonations;
	}

	public List<Donor> getTopDonors() {
		return topDonors;
	}

	public void setTopDonors(List<Donor> topDonors) {
		this.topDonors = topDonors;
	}

}
