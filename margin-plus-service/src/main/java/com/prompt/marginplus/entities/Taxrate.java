package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the taxrates database table.
 * 
 */
@Entity
@Table(name="taxrates")
@NamedQuery(name="Taxrate.findAll", query="SELECT t FROM Taxrate t")
public class Taxrate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int TR_TaxId;

	@Column(name="TR_CREATION_TIMESTAMP")
	private Timestamp trCreationTimestamp;

	@Column(name="TR_MODIFICATION_TIMESTAMP")
	private Timestamp trModificationTimestamp;

	private BigDecimal TR_TaxRate;

	private String TR_TaxType;

	//bi-directional many-to-one association to Inventoryrefilldetail
	@OneToMany(mappedBy="taxrate")
	private List<Inventoryrefilldetail> inventoryrefilldetails;

	public Taxrate() {
	}

	public int getTR_TaxId() {
		return this.TR_TaxId;
	}

	public void setTR_TaxId(int TR_TaxId) {
		this.TR_TaxId = TR_TaxId;
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

	public BigDecimal getTR_TaxRate() {
		return this.TR_TaxRate;
	}

	public void setTR_TaxRate(BigDecimal TR_TaxRate) {
		this.TR_TaxRate = TR_TaxRate;
	}

	public String getTR_TaxType() {
		return this.TR_TaxType;
	}

	public void setTR_TaxType(String TR_TaxType) {
		this.TR_TaxType = TR_TaxType;
	}

	public List<Inventoryrefilldetail> getInventoryrefilldetails() {
		return this.inventoryrefilldetails;
	}

	public void setInventoryrefilldetails(List<Inventoryrefilldetail> inventoryrefilldetails) {
		this.inventoryrefilldetails = inventoryrefilldetails;
	}

	public Inventoryrefilldetail addInventoryrefilldetail(Inventoryrefilldetail inventoryrefilldetail) {
		getInventoryrefilldetails().add(inventoryrefilldetail);
		inventoryrefilldetail.setTaxrate(this);

		return inventoryrefilldetail;
	}

	public Inventoryrefilldetail removeInventoryrefilldetail(Inventoryrefilldetail inventoryrefilldetail) {
		getInventoryrefilldetails().remove(inventoryrefilldetail);
		inventoryrefilldetail.setTaxrate(null);

		return inventoryrefilldetail;
	}

}