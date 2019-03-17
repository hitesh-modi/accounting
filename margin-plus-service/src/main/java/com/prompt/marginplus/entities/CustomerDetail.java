package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the customer_details database table.
 * 
 */
@Entity
@Table(name="customer_details")
@NamedQuery(name="CustomerDetail.findAll", query="SELECT c FROM CustomerDetail c")
public class CustomerDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CD_CUSTOMER_ID")
	private long cdCustomerId;

	@Column(name="CD_CREATIONTIMESTAMP")
	private Timestamp cdCreationtimestamp;

	@Column(name="CD_CUSTOMER_ADDRESS")
	private String cdCustomerAddress;

	@Column(name="CD_CUSTOMER_EMAIL")
	private String cdCustomerEmail;

	@Column(name="CD_CUSTOMER_NAME")
	private String cdCustomerName;

	@Column(name="CD_CUSTOMER_MOBILE")
	private String cdCustomerMobile;

	@Column(name="CD_CUSTOMER_PHONE")
	private String cdCustomerPhone;

	@Column(name="CD_MODIFICATIONTIMESTAMP")
	private Timestamp cdModificationtimestamp;

	@ManyToOne
	@JoinColumn(name="CD_USERID")
	private User user;

	@ManyToOne
	@JoinColumn(name="CD_CUSTOMER_STATE_CODE")
	private State state;
	
	/*@Column(name="CD_CUSTOMER_STATE")
	private String cdCustomerState;
	
	@Column(name="CD_CUSTOMER_STATE_CODE")
	private String cdCustomerStateCode;*/
	
	@Column(name="CD_GSTIN")
	private String cdCustomerGSTIN;


	public String getCdCustomerGSTIN() {
		return cdCustomerGSTIN;
	}

	public void setCdCustomerGSTIN(String cdCustomerGSTIN) {
		this.cdCustomerGSTIN = cdCustomerGSTIN;
	}

	//bi-directional many-to-one association to Invoicedetail
	@OneToMany(mappedBy="customerDetail")
	private List<Invoicedetail> invoicedetails;

	public CustomerDetail() {
	}

	public long getCdCustomerId() {
		return this.cdCustomerId;
	}

	public void setCdCustomerId(long cdCustomerId) {
		this.cdCustomerId = cdCustomerId;
	}

	public Timestamp getCdCreationtimestamp() {
		return this.cdCreationtimestamp;
	}

	public void setCdCreationtimestamp(Timestamp cdCreationtimestamp) {
		this.cdCreationtimestamp = cdCreationtimestamp;
	}

	public String getCdCustomerAddress() {
		return this.cdCustomerAddress;
	}

	public void setCdCustomerAddress(String cdCustomerAddress) {
		this.cdCustomerAddress = cdCustomerAddress;
	}

	public String getCdCustomerEmail() {
		return this.cdCustomerEmail;
	}

	public void setCdCustomerEmail(String cdCustomerEmail) {
		this.cdCustomerEmail = cdCustomerEmail;
	}

	public String getCdCustomerName() {
		return this.cdCustomerName;
	}

	public void setCdCustomerName(String cdCustomerName) {
		this.cdCustomerName = cdCustomerName;
	}

	public String getCdCustomerMobile() {
		return this.cdCustomerMobile;
	}

	public void setCdCustomerMobile(String cdCustomerMobile) {
		this.cdCustomerMobile = cdCustomerMobile;
	}

	public String getCdCustomerPhone() {
		return this.cdCustomerPhone;
	}

	public void setCdCustomerPhone(String cdCustomerPhone) {
		this.cdCustomerPhone = cdCustomerPhone;
	}

	public Timestamp getCdModificationtimestamp() {
		return this.cdModificationtimestamp;
	}

	public void setCdModificationtimestamp(Timestamp cdModificationtimestamp) {
		this.cdModificationtimestamp = cdModificationtimestamp;
	}

	public List<Invoicedetail> getInvoicedetails() {
		return this.invoicedetails;
	}

	public void setInvoicedetails(List<Invoicedetail> invoicedetails) {
		this.invoicedetails = invoicedetails;
	}

	public Invoicedetail addInvoicedetail(Invoicedetail invoicedetail) {
		getInvoicedetails().add(invoicedetail);
		invoicedetail.setCustomerDetail(this);

		return invoicedetail;
	}

	public Invoicedetail removeInvoicedetail(Invoicedetail invoicedetail) {
		getInvoicedetails().remove(invoicedetail);
		invoicedetail.setCustomerDetail(null);

		return invoicedetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}