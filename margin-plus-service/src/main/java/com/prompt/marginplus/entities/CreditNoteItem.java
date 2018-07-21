package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the credit_note_items database table.
 * 
 */
@Entity
@Table(name="credit_note_items")
@NamedQuery(name="CreditNoteItem.findAll", query="SELECT c FROM CreditNoteItem c")
public class CreditNoteItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cni_itemid")
	private String cniItemid;

	@Column(name="cni_discount")
	private BigDecimal cniDiscount;

	@Column(name="cni_productid")
	private int cniProductid;

	@Column(name="cni_quantity")
	private int cniQuantity;

	@Column(name="cni_rate")
	private BigDecimal cniRate;

	@Column(name="cni_totalamount")
	private BigDecimal cniTotalamount;

	@Column(name="cni_totaltaxablevalue")
	private BigDecimal cniTotaltaxablevalue;

	@Column(name="cni_unit")
	private String cniUnit;

	//bi-directional many-to-one association to CreditNote
	@ManyToOne
	@JoinColumn(name="cni_creditnoteid")
	private CreditNote creditNote;

	//bi-directional many-to-one association to CreditNoteTaxItem
	@OneToMany(mappedBy="creditNoteItem")
	private Set<CreditNoteTaxItem> creditNoteTaxItems;

	public CreditNoteItem() {
	}

	public String getCniItemid() {
		return this.cniItemid;
	}

	public void setCniItemid(String cniItemid) {
		this.cniItemid = cniItemid;
	}

	public BigDecimal getCniDiscount() {
		return this.cniDiscount;
	}

	public void setCniDiscount(BigDecimal cniDiscount) {
		this.cniDiscount = cniDiscount;
	}

	public int getCniProductid() {
		return this.cniProductid;
	}

	public void setCniProductid(int cniProductid) {
		this.cniProductid = cniProductid;
	}

	public int getCniQuantity() {
		return this.cniQuantity;
	}

	public void setCniQuantity(int cniQuantity) {
		this.cniQuantity = cniQuantity;
	}

	public BigDecimal getCniRate() {
		return this.cniRate;
	}

	public void setCniRate(BigDecimal cniRate) {
		this.cniRate = cniRate;
	}

	public BigDecimal getCniTotalamount() {
		return this.cniTotalamount;
	}

	public void setCniTotalamount(BigDecimal cniTotalamount) {
		this.cniTotalamount = cniTotalamount;
	}

	public BigDecimal getCniTotaltaxablevalue() {
		return this.cniTotaltaxablevalue;
	}

	public void setCniTotaltaxablevalue(BigDecimal cniTotaltaxablevalue) {
		this.cniTotaltaxablevalue = cniTotaltaxablevalue;
	}

	public String getCniUnit() {
		return this.cniUnit;
	}

	public void setCniUnit(String cniUnit) {
		this.cniUnit = cniUnit;
	}

	public CreditNote getCreditNote() {
		return this.creditNote;
	}

	public void setCreditNote(CreditNote creditNote) {
		this.creditNote = creditNote;
	}

	public Set<CreditNoteTaxItem> getCreditNoteTaxItems() {
		return this.creditNoteTaxItems;
	}

	public void setCreditNoteTaxItems(Set<CreditNoteTaxItem> creditNoteTaxItems) {
		this.creditNoteTaxItems = creditNoteTaxItems;
	}

	public CreditNoteTaxItem addCreditNoteTaxItem(CreditNoteTaxItem creditNoteTaxItem) {
		getCreditNoteTaxItems().add(creditNoteTaxItem);
		creditNoteTaxItem.setCreditNoteItem(this);

		return creditNoteTaxItem;
	}

	public CreditNoteTaxItem removeCreditNoteTaxItem(CreditNoteTaxItem creditNoteTaxItem) {
		getCreditNoteTaxItems().remove(creditNoteTaxItem);
		creditNoteTaxItem.setCreditNoteItem(null);

		return creditNoteTaxItem;
	}

}