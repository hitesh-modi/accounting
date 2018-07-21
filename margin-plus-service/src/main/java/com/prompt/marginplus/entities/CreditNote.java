package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the credit_note database table.
 * 
 */
@Entity
@Table(name="credit_note")
@NamedQuery(name="CreditNote.findAll", query="SELECT c FROM CreditNote c")
public class CreditNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cn_creditnoteid")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private String cnCreditnoteid;

	@Column(name="cn_consigneeid")
	private int cnConsigneeid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cn_createdtimestamp")
	private Date cnCreatedtimestamp;

	@Column(name="cn_customerid")
	private int cnCustomerid;

	@Temporal(TemporalType.DATE)
	@Column(name="cn_date")
	private Date cnDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cn_modifiedtimestamp")
	private Date cnModifiedtimestamp;

	@Column(name="cn_parentinvoiceid")
	private java.math.BigInteger cnParentinvoiceid;

	@Column(name="cn_totalamount")
	private BigDecimal cnTotalamount;

	@Column(name="cn_totaltax")
	private BigDecimal cnTotaltax;

	@Column(name="cn_userid")
	private String cnUserid;

	//bi-directional many-to-one association to CreditNoteItem
	@OneToMany(mappedBy="creditNote")
	private Set<CreditNoteItem> creditNoteItems;

	public CreditNote() {
	}

	public String getCnCreditnoteid() {
		return this.cnCreditnoteid;
	}

	public void setCnCreditnoteid(String cnCreditnoteid) {
		this.cnCreditnoteid = cnCreditnoteid;
	}

	public int getCnConsigneeid() {
		return this.cnConsigneeid;
	}

	public void setCnConsigneeid(int cnConsigneeid) {
		this.cnConsigneeid = cnConsigneeid;
	}

	public Date getCnCreatedtimestamp() {
		return this.cnCreatedtimestamp;
	}

	public void setCnCreatedtimestamp(Date cnCreatedtimestamp) {
		this.cnCreatedtimestamp = cnCreatedtimestamp;
	}

	public int getCnCustomerid() {
		return this.cnCustomerid;
	}

	public void setCnCustomerid(int cnCustomerid) {
		this.cnCustomerid = cnCustomerid;
	}

	public Date getCnDate() {
		return this.cnDate;
	}

	public void setCnDate(Date cnDate) {
		this.cnDate = cnDate;
	}

	public Date getCnModifiedtimestamp() {
		return this.cnModifiedtimestamp;
	}

	public void setCnModifiedtimestamp(Date cnModifiedtimestamp) {
		this.cnModifiedtimestamp = cnModifiedtimestamp;
	}

	public java.math.BigInteger getCnParentinvoiceid() {
		return this.cnParentinvoiceid;
	}

	public void setCnParentinvoiceid(java.math.BigInteger cnParentinvoiceid) {
		this.cnParentinvoiceid = cnParentinvoiceid;
	}

	public BigDecimal getCnTotalamount() {
		return this.cnTotalamount;
	}

	public void setCnTotalamount(BigDecimal cnTotalamount) {
		this.cnTotalamount = cnTotalamount;
	}

	public BigDecimal getCnTotaltax() {
		return this.cnTotaltax;
	}

	public void setCnTotaltax(BigDecimal cnTotaltax) {
		this.cnTotaltax = cnTotaltax;
	}

	public String getCnUserid() {
		return this.cnUserid;
	}

	public void setCnUserid(String cnUserid) {
		this.cnUserid = cnUserid;
	}

	public Set<CreditNoteItem> getCreditNoteItems() {
		return this.creditNoteItems;
	}

	public void setCreditNoteItems(Set<CreditNoteItem> creditNoteItems) {
		this.creditNoteItems = creditNoteItems;
	}

	public CreditNoteItem addCreditNoteItem(CreditNoteItem creditNoteItem) {
		getCreditNoteItems().add(creditNoteItem);
		creditNoteItem.setCreditNote(this);

		return creditNoteItem;
	}

	public CreditNoteItem removeCreditNoteItem(CreditNoteItem creditNoteItem) {
		getCreditNoteItems().remove(creditNoteItem);
		creditNoteItem.setCreditNote(null);

		return creditNoteItem;
	}

}