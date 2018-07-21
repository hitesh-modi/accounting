package com.prompt.marginplus.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the invoicedetails database table.
 * 
 */
@Entity
@Table(name="invoicedetails")
@NamedQuery(name="Invoicedetail.findAll", query="SELECT i FROM Invoicedetail i")
public class Invoicedetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_invoiceid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String ID_InvoiceId;

	@Column(name="id_creationtimestamp")
	private Timestamp ID_CreationTimestamp;
	
	@Column(name="id_invoicenumber")
	private String ID_InvoiceNumber;

	@Column(name="id_invoicebalanceamount")
	private BigDecimal ID_InvoiceBalanceAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="id_invoicedate")
	private Date ID_InvoiceDate;
	
	@Column(name="id_grandtotal")
	private BigDecimal ID_GrandTotal;


	@Column(name="id_invoicepaidamount")
	private BigDecimal ID_InvoicePaidAmount;

	@Column(name="id_invoicetotalamount")
	private BigDecimal ID_InvoiceTotalAmount;

	@Column(name="id_modificationtimestamp")
	private Timestamp ID_ModificationTimestamp;

	@Column(name="id_taxamount")
	private BigDecimal ID_TaxAmount;
	
	@Temporal(TemporalType.DATE)
	@Column(name="id_invoiceduedate")
	private Date ID_InvoiceDueDate;

	//bi-directional many-to-one association to Invoiceitemdetail
	@OneToMany(mappedBy="invoicedetail", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Invoiceitemdetail> invoiceitemdetails;

	//bi-directional many-to-one association to CustomerDetail
	@ManyToOne
	@JoinColumn(name="id_customerid")
	private CustomerDetail customerDetail;

	//bi-directional many-to-one association to ConsigneeDetail
	@ManyToOne
	@JoinColumn(name="id_consigneeid")
	private ConsigneeDetail consigneeDetail;

	public Invoicedetail() {
	}
	
	public String getID_InvoiceNumber() {
		return ID_InvoiceNumber;
	}

	public void setID_InvoiceNumber(String iD_InvoiceNumber) {
		ID_InvoiceNumber = iD_InvoiceNumber;
	}

	public String getID_InvoiceId() {
		return this.ID_InvoiceId;
	}

	public void setID_InvoiceId(String ID_InvoiceId) {
		this.ID_InvoiceId = ID_InvoiceId;
	}

	public Timestamp getID_CreationTimestamp() {
		return this.ID_CreationTimestamp;
	}

	public void setID_CreationTimestamp(Timestamp ID_CreationTimestamp) {
		this.ID_CreationTimestamp = ID_CreationTimestamp;
	}

	public BigDecimal getID_InvoiceBalanceAmount() {
		return this.ID_InvoiceBalanceAmount;
	}

	public void setID_InvoiceBalanceAmount(BigDecimal ID_InvoiceBalanceAmount) {
		this.ID_InvoiceBalanceAmount = ID_InvoiceBalanceAmount;
	}

	public Date getID_InvoiceDate() {
		return this.ID_InvoiceDate;
	}

	public void setID_InvoiceDate(Date ID_InvoiceDate) {
		this.ID_InvoiceDate = ID_InvoiceDate;
	}

	public BigDecimal getID_InvoicePaidAmount() {
		return this.ID_InvoicePaidAmount;
	}

	public void setID_InvoicePaidAmount(BigDecimal ID_InvoicePaidAmount) {
		this.ID_InvoicePaidAmount = ID_InvoicePaidAmount;
	}

	public BigDecimal getID_InvoiceTotalAmount() {
		return this.ID_InvoiceTotalAmount;
	}

	public void setID_InvoiceTotalAmount(BigDecimal ID_InvoiceTotalAmount) {
		this.ID_InvoiceTotalAmount = ID_InvoiceTotalAmount;
	}

	public Timestamp getID_ModificationTimestamp() {
		return this.ID_ModificationTimestamp;
	}

	public void setID_ModificationTimestamp(Timestamp ID_ModificationTimestamp) {
		this.ID_ModificationTimestamp = ID_ModificationTimestamp;
	}

	public BigDecimal getID_TaxAmount() {
		return this.ID_TaxAmount;
	}

	public void setID_TaxAmount(BigDecimal ID_TaxAmount) {
		this.ID_TaxAmount = ID_TaxAmount;
	}

	public Set<Invoiceitemdetail> getInvoiceitemdetails() {
		return this.invoiceitemdetails;
	}

	public void setInvoiceitemdetails(Set<Invoiceitemdetail> invoiceitemdetails) {
		this.invoiceitemdetails = invoiceitemdetails;
	}

	public Invoiceitemdetail addInvoiceitemdetail(Invoiceitemdetail invoiceitemdetail) {
		getInvoiceitemdetails().add(invoiceitemdetail);
		invoiceitemdetail.setInvoicedetail(this);

		return invoiceitemdetail;
	}

	public Invoiceitemdetail removeInvoiceitemdetail(Invoiceitemdetail invoiceitemdetail) {
		getInvoiceitemdetails().remove(invoiceitemdetail);
		invoiceitemdetail.setInvoicedetail(null);

		return invoiceitemdetail;
	}

	public CustomerDetail getCustomerDetail() {
		return this.customerDetail;
	}

	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
	}


	public ConsigneeDetail getConsigneeDetail() {
		return this.consigneeDetail;
	}

	public void setConsigneeDetail(ConsigneeDetail consigneeDetail) {
		this.consigneeDetail = consigneeDetail;
	}
	
	public BigDecimal getID_GrandTotal() {
		return ID_GrandTotal;
	}

	public void setID_GrandTotal(BigDecimal iD_GrandTotal) {
		ID_GrandTotal = iD_GrandTotal;
	}

	public Date getID_InvoiceDueDate() {
		return ID_InvoiceDueDate;
	}

	public void setID_InvoiceDueDate(Date iD_InvoiceDueDate) {
		ID_InvoiceDueDate = iD_InvoiceDueDate;
	}

}