package com.prompt.marginplus.models;

public enum ResponseStatus {

	RESPONSE_SUCCESS("success"),
	
	RESPONSE_FAILURE("failure"),
	
	RESPONSE_USER_EMAIL_EXISTS("user_email_exists"),
	
	RESPONSE_USER_MOBILE_EXISTS("user_mobile_exists");
	
	private ResponseStatus(String status) {
		this.status = status;
	}
	
	private String status;
	
	public String getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return this.status;
	}
	
}
