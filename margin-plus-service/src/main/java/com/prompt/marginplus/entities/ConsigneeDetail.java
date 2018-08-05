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
@Table(name="consignee_details")
@NamedQuery(name="ConsigneeDetail.findAll", query="SELECT c FROM ConsigneeDetail c")
public class ConsigneeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CONSIGNEE_ID")
	private Long consigneeId;

	@Column(name="CONSIGNEE_CREATIONTIMESTAMP")
	private Timestamp consigneeCreationTimestamp;

	@Column(name="CONSIGNEE_ADRESS")
	private String consigneeAddress;

	@Column(name="CONSIGNEE_EMAIL")
	private String consigneeEmail;

	@Column(name="CONSIGNEE_NAME")
	private String consigneeName;

	@Column(name="CONSIGNEE_MOBILE")
	private String consigneeMobile;

	@Column(name="CONSIGNEE_PHONE")
	private String consigneePhone;

	@Column(name="CONSIGNEE_MODIFICATIONTIMESTAMP")
	private Timestamp consigneeModificationTime;
	
	/*@Column(name="CONSIGNEE_STATE")
	private String consigneeState;
	
	@Column(name="CONSIGNEE_STATE_CODE")
	private String consigneeStateCode;*/
	
	@ManyToOne
	@JoinColumn(name="CONSIGNEE_STATE_CODE")
	private State state;

	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;
	
	@Column(name="CONSIGNEE_GSTIN")
	private String consigneeGSTIN;


	public String getConsigneeGSTIN() {
		return consigneeGSTIN;
	}

	public void setConsigneeGSTIN(String consigneeGSTIN) {
		this.consigneeGSTIN = consigneeGSTIN;
	}

	//bi-directional many-to-one association to Invoicedetail
	@OneToMany(mappedBy="customerDetail")
	private List<Invoicedetail> invoicedetails;

	public ConsigneeDetail() {
	}

	public Long getConsigneeId() {
		return this.consigneeId;
	}

	public void setConsigneeId(Long consigneeId) {
		this.consigneeId = consigneeId;
	}

	public Timestamp getConsigneeCreationTimestamp() {
		return this.consigneeCreationTimestamp;
	}

	public void setConsigneeCreationTimestamp(Timestamp consigneeCreationTimestamp) {
		this.consigneeCreationTimestamp = consigneeCreationTimestamp;
	}

	public String getConsigneeAddress() {
		return this.consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeEmail() {
		return this.consigneeEmail;
	}

	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}

	public String getConsigneeName() {
		return this.consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeMobile() {
		return this.consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public String getConsigneePhone() {
		return this.consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public Timestamp getConsigneeModificationTime() {
		return this.consigneeModificationTime;
	}

	public void setConsigneeModificationTime(Timestamp consigneeModificationTime) {
		this.consigneeModificationTime = consigneeModificationTime;
	}

	public List<Invoicedetail> getInvoicedetails() {
		return this.invoicedetails;
	}

	public void setInvoicedetails(List<Invoicedetail> invoicedetails) {
		this.invoicedetails = invoicedetails;
	}

	public Invoicedetail addInvoicedetail(Invoicedetail invoicedetail) {
		getInvoicedetails().add(invoicedetail);
		invoicedetail.setConsigneeDetail(this);
		return invoicedetail;
	}

	public Invoicedetail removeInvoicedetail(Invoicedetail invoicedetail) {
		getInvoicedetails().remove(invoicedetail);
		invoicedetail.setCustomerDetail(null);

		return invoicedetail;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}