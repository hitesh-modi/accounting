package com.prompt.marginplus.models;

public class UserModel {

	private String userid;
	
	private String userName;
	
	private String fname;
	
	private String mname;
	
	private String lname;
	
	private String address;
	
	private String gstin;
	
	private String password;
	
	private String confirmPassword;
	
	private String email;
	
	private String firmName;
	
	private String contactNumber;
	
	private StateModel state;

	private boolean isUserAuthenticated;
	
	public StateModel getState() {
		return state;
	}

	public void setState(StateModel state) {
		this.state = state;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isUserAuthenticated() {
		return isUserAuthenticated;
	}

	public void setUserAuthenticated(boolean userAuthenticated) {
		isUserAuthenticated = userAuthenticated;
	}

	@Override
	public String toString() {
		return "UserModel [userid=" + userid + ", userName=" + userName + ", fname=" + fname + ", mname=" + mname
				+ ", lname=" + lname + ", address=" + address + ", gstin=" + gstin + ", password=" + password
				+ ", email=" + email + ", firmName=" + firmName + ", contactNumber=" + contactNumber + ", state="
				+ state + "]";
	}

	
	
}
