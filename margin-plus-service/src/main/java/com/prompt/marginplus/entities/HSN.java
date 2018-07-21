package com.prompt.marginplus.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the taxrates database table.
 * 
 */
@Entity
@Table(name="hsnmaster")
@NamedQuery(name="HSN.findAll", query="SELECT h FROM HSN h")
public class HSN implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="hsncode")
	private String hsnCode;

	@Column(name="hsndesc")
	private String hsnDesc;

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getHsnDesc() {
		return hsnDesc;
	}

	public void setHsnDesc(String hsnDesc) {
		this.hsnDesc = hsnDesc;
	}

	
}