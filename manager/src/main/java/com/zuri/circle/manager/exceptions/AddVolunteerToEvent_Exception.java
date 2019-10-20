package com.zuri.circle.manager.exceptions;

public class AddVolunteerToEvent_Exception extends Exception{

	private static final long serialVersionUID = 1L;
	private final String errorMessage;

	public AddVolunteerToEvent_Exception(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
