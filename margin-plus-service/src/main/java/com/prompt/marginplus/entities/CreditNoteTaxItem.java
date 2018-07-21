package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the credit_note_tax_items database table.
 * 
 */
@Entity
@Table(name="credit_note_tax_items")
@NamedQuery(name="CreditNoteTaxItem.findAll", query="SELECT c FROM CreditNoteTaxItem c")
public class CreditNoteTaxItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cnt_taxid")
	private String cntTaxid;

	@Column(name="cnt_taxamount")
	private BigDecimal cntTaxamount;

	@Column(name="cnt_taxrate")
	private BigDecimal cntTaxrate;

	@Column(name="cnt_taxtype")
	private String cntTaxtype;

	//bi-directional many-to-one association to CreditNoteItem
	@ManyToOne
	@JoinColumn(name="cnt_creditnoteitemid")
	private CreditNoteItem creditNoteItem;

	public CreditNoteTaxItem() {
	}

	public String getCntTaxid() {
		return this.cntTaxid;
	}

	public void setCntTaxid(String cntTaxid) {
		this.cntTaxid = cntTaxid;
	}

	public BigDecimal getCntTaxamount() {
		return this.cntTaxamount;
	}

	public void setCntTaxamount(BigDecimal cntTaxamount) {
		this.cntTaxamount = cntTaxamount;
	}

	public BigDecimal getCntTaxrate() {
		return this.cntTaxrate;
	}

	public void setCntTaxrate(BigDecimal cntTaxrate) {
		this.cntTaxrate = cntTaxrate;
	}

	public String getCntTaxtype() {
		return this.cntTaxtype;
	}

	public void setCntTaxtype(String cntTaxtype) {
		this.cntTaxtype = cntTaxtype;
	}

	public CreditNoteItem getCreditNoteItem() {
		return this.creditNoteItem;
	}

	public void setCreditNoteItem(CreditNoteItem creditNoteItem) {
		this.creditNoteItem = creditNoteItem;
	}

}