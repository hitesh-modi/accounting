package com.prompt.marginplus.models;

public class Customer {
	
	private long customerId;

	private String name;
	
	private String address;
	
	private String state;
	
	private String stateCode;
	
	private String gstin;
	
	private String email;
	
	private String mobileNo;
	
	private String phoneNo;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerId=" + customerId +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", state='" + state + '\'' +
				", stateCode='" + stateCode + '\'' +
				", gstin='" + gstin + '\'' +
				", email='" + email + '\'' +
				", mobileNo='" + mobileNo + '\'' +
				", phoneNo='" + phoneNo + '\'' +
				'}';
	}
}
