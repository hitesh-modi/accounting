package com.prompt.marginplus.models;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 */
public class Invoice {

	private String invoiceNumber;
	private Date invoiceDate;
	private Date invoiceDueDate;
	private Customer customer;
	private Consignee consignee;
	private Collection<InvoiceItem> invoiceItemDetails;
	private BigDecimal grandTotal;
	private BigDecimal totalTax;
	private BigDecimal amountReceived;
	private BigDecimal netTotal;
	private boolean newCustomer;
	private String newConsignee;

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Consignee getConsignee() {
		return consignee;
	}

	public void setConsignee(Consignee consignee) {
		this.consignee = consignee;
	}

	public Collection<InvoiceItem> getInvoiceItemDetails() {
		return invoiceItemDetails;
	}

	public void setInvoiceItemDetails(Collection<InvoiceItem> invoiceItemDetails) {
		this.invoiceItemDetails = invoiceItemDetails;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(BigDecimal amountReceived) {
		this.amountReceived = amountReceived;
	}

	public boolean isNewCustomer() {
		return newCustomer;
	}

	public void setNewCustomer(boolean newCustomer) {
		this.newCustomer = newCustomer;
	}

	public String getNewConsignee() {
		return newConsignee;
	}

	public void setNewConsignee(String newConsignee) {
		this.newConsignee = newConsignee;
	}

	public BigDecimal getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(BigDecimal netTotal) {
		this.netTotal = netTotal;
	}

	public Date getInvoiceDueDate() {
		return invoiceDueDate;
	}

	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	@Override
	public String toString() {
		return "Invoice{" + "invoiceNumber='" + invoiceNumber + '\'' + ", invoiceDate='" + invoiceDate + '\''
				+ ", customer=" + customer + ", consignee=" + consignee + ", invoiceItemDetails=" + invoiceItemDetails
				+ ", grandTotal=" + grandTotal + ", totalTax=" + totalTax + ", amountReceived=" + amountReceived
				+ ", newCustomer=" + newCustomer + ", newConsignee='" + newConsignee + '\'' + '}';
	}
}
