package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the inventoryrefilldetails database table.
 * 
 */
@Entity
@Table(name="inventoryrefilldetails")
@NamedQuery(name="Inventoryrefilldetail.findAll", query="SELECT i FROM Inventoryrefilldetail i")
public class Inventoryrefilldetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IRD_ID")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int IRD_ProductQuantity;

	private BigDecimal IRD_RefillAmount;

	@Temporal(TemporalType.DATE)
	private Date IRD_RefillDate;

	private BigDecimal IRD_TaxAmount;

	private BigDecimal IRD_TotalPaidAmount;

	@Column(name="TR_CREATION_TIMESTAMP")
	private Timestamp trCreationTimestamp;

	@Column(name="TR_MODIFICATION_TIMESTAMP")
	private Timestamp trModificationTimestamp;

	//bi-directional many-to-one association to Productdetail
	@ManyToOne
	@JoinColumn(name="IRD_ProductID")
	private Productdetail productdetail;

	//bi-directional many-to-one association to Taxrate
	@ManyToOne
	@JoinColumn(name="IRD_TaxId")
	private Taxrate taxrate;

	public Inventoryrefilldetail() {
	}

	public int getIRD_ProductQuantity() {
		return this.IRD_ProductQuantity;
	}

	public void setIRD_ProductQuantity(int IRD_ProductQuantity) {
		this.IRD_ProductQuantity = IRD_ProductQuantity;
	}

	public BigDecimal getIRD_RefillAmount() {
		return this.IRD_RefillAmount;
	}

	public void setIRD_RefillAmount(BigDecimal IRD_RefillAmount) {
		this.IRD_RefillAmount = IRD_RefillAmount;
	}

	public Date getIRD_RefillDate() {
		return this.IRD_RefillDate;
	}

	public void setIRD_RefillDate(Date IRD_RefillDate) {
		this.IRD_RefillDate = IRD_RefillDate;
	}

	public BigDecimal getIRD_TaxAmount() {
		return this.IRD_TaxAmount;
	}

	public void setIRD_TaxAmount(BigDecimal IRD_TaxAmount) {
		this.IRD_TaxAmount = IRD_TaxAmount;
	}

	public BigDecimal getIRD_TotalPaidAmount() {
		return this.IRD_TotalPaidAmount;
	}

	public void setIRD_TotalPaidAmount(BigDecimal IRD_TotalPaidAmount) {
		this.IRD_TotalPaidAmount = IRD_TotalPaidAmount;
	}

	public Timestamp getTrCreationTimestamp() {
		return this.trCreationTimestamp;
	}

	public void setTrCreationTimestamp(Timestamp trCreationTimestamp) {
		this.trCreationTimestamp = trCreationTimestamp;
	}

	public Timestamp getTrModificationTimestamp() {
		return this.trModificationTimestamp;
	}

	public void setTrModificationTimestamp(Timestamp trModificationTimestamp) {
		this.trModificationTimestamp = trModificationTimestamp;
	}

	public Productdetail getProductdetail() {
		return this.productdetail;
	}

	public void setProductdetail(Productdetail productdetail) {
		this.productdetail = productdetail;
	}

	public Taxrate getTaxrate() {
		return this.taxrate;
	}

	public void setTaxrate(Taxrate taxrate) {
		this.taxrate = taxrate;
	}

}