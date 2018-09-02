package com.prompt.marginplus.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;


/**
 * The persistent class for the invoicedetails database table.
 * 
 */
@Entity
@Table(name="invoicenumberdetails")
@NamedQuery(name="InvoiceNumberDetail.findAll", query="SELECT i FROM InvoiceNumberDetail i")
public class InvoiceNumberDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	@Column(name="invoicedate")
	private Date invoiceDate;
	
	@Column(name="sequenceno")
	private int sequenceNo;

	@ManyToOne
	@JoinColumn(name="userid")
	private User user;


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	
}