package com.prompt.marginplus.models;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	private long productId;
	private String type;
	private String name;
	private String company;
	private Date agencyStartDate;
	private BigDecimal depositAmount;
	private String hsnCode;
	private float taxRate;
	private boolean isGood;
	private String accountingCodeDesc;
	
	public String getAccountingCodeDesc() {
		return accountingCodeDesc;
	}
	public void setAccountingCodeDesc(String accountingCodeDesc) {
		this.accountingCodeDesc = accountingCodeDesc;
	}
	public float getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}
	public boolean isGood() {
		return isGood;
	}
	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String productType) {
		this.type = productType;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Date getAgencyStartDate() {
		return agencyStartDate;
	}
	public void setAgencyStartDate(Date agencyStartDate) {
		this.agencyStartDate = agencyStartDate;
	}
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(BigDecimal securityDeposit) {
		this.depositAmount = securityDeposit;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "Product[ productId: " + productId + ", productType: "+ type +", companyName: "+company+", agencyStartDate:"+agencyStartDate+", securityDeposit:"+depositAmount+" ]";
				
	}
	
}
