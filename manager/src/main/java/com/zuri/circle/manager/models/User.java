package com.zuri.circle.manager.models;

import java.io.Serializable;

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
	public User(String firstName, String lastName, String email, String address, String city, String state, String zip,
			String country, String userType, String preferredContact) {
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
	
	
	
	
	
	

}
