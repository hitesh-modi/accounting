package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the roles_permissions database table.
 * 
 */
//@Entity
@Table(name="roles_permissions")
@NamedQuery(name="RolesPermission.findAll", query="SELECT r FROM RolesPermission r")
public class RolesPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	private String permission;

	@Column(name="role_name")
	private String roleName;

	public RolesPermission() {
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}