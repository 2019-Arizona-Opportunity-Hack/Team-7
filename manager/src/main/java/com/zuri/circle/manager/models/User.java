package com.zuri.circle.manager.models;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String userType;
	private String preferredContact;
	private String phoneNumber;
	private String isMarried;
	private String isChildren;
	private List<String> helpType;
	private String reason;
	
	public User(String firstName, String lastName, String email, String address, String city, String state, String zip,
			String country, String userType, String preferredContact, String phoneNumber, String isMarried,
			String isChildren, List<String> helpType, String reason) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.userType = userType;
		this.preferredContact = preferredContact;
		this.phoneNumber = phoneNumber;
		this.isMarried = isMarried;
		this.isChildren = isChildren;
		this.helpType = helpType;
		this.reason = reason;
	}


	public User() {
		
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getPreferredContact() {
		return preferredContact;
	}


	public void setPreferredContact(String preferredContact) {
		this.preferredContact = preferredContact;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getIsMarried() {
		return isMarried;
	}


	public void setIsMarried(String isMarried) {
		this.isMarried = isMarried;
	}


	public String getIsChildren() {
		return isChildren;
	}


	public void setIsChildren(String isChildren) {
		this.isChildren = isChildren;
	}


	public List<String> getHelpType() {
		return helpType;
	}


	public void setHelpType(List<String> helpType) {
		this.helpType = helpType;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}

	
}
