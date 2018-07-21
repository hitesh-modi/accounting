package com.prompt.marginplus.models;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class will be used as DTO for sales report
 * @author Hitesh Modi
 *
 */
public class InvoiceReportModel {
	
	private String invoiceNumber;
	
	private String invoiceId;
	
	private String customerName;
	
	private String consigneeName;
	
	private BigDecimal invoiceAmount;
	
	private BigDecimal balanceAmount;
	
	private Date invoiceDate;
	
	private Date invoiceDueDate;
	
	private boolean isDanger;

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public Date getInvoiceDueDate() {
		return invoiceDueDate;
	}

	public boolean isDanger() {
		return isDanger;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	public void setDanger(boolean isDanger) {
		this.isDanger = isDanger;
	}
	
	

}
