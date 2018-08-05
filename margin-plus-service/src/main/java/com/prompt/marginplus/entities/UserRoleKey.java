package com.prompt.marginplus.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserRoleKey implements Serializable{
	
	private String roleName;
	
	private String userid;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String username) {
		this.userid = username;
	}
	
}
