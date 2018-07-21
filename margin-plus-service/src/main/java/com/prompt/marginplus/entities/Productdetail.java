package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the productdetails database table.
 * 
 */
@Entity
@Table(name="productdetails")
@NamedQuery(name="Productdetail.findAll", query="SELECT p FROM Productdetail p")
public class Productdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pd_productid")
	private long productId;

	@Column(name="pd_agencysecuritydeposit")
	private BigDecimal agencySecurityDeposit;
	
	@Column(name="pd_serviceorgood")
	private String productServiceOrGood;

	@Temporal(TemporalType.DATE)
	@Column(name="pd_agencystartdate")
	private Date agencyStartDate;

	@Column(name="PD_CREATION_TIMESTAMP")
	private Timestamp pdCreationTimestamp;

	@Column(name="PD_MODIFICATION_TIMESTAMP")
	private Timestamp pdModificationTimestamp;

	@Column(name="pd_productcompany")
	private String productCompany;

	@Column(name="pd_producttype")
	private String productType;
	
	@Column(name="pd_productname")
	private String productName;
	
	@Column(name="pd_taxrate")
	private float productTaxRate;
	
	@OneToOne
	@JoinColumn(name="pd_hsn")
	private HSN productHSN;
	
	@OneToOne
	@JoinColumn(name="pd_sac")
	private SacMaster productSac;

	//bi-directional many-to-one association to Inventorydetail
	@OneToMany(mappedBy="productdetail")
	private List<Inventorydetail> inventorydetails;

	//bi-directional many-to-one association to Inventoryrefilldetail
	@OneToMany(mappedBy="productdetail")
	private List<Inventoryrefilldetail> inventoryrefilldetails;

	//bi-directional many-to-one association to Invoiceitemdetail
	@OneToMany(mappedBy="productdetail")
	private List<Invoiceitemdetail> invoiceitemdetails;

	public Productdetail() {
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public BigDecimal getAgencySecurityDeposit() {
		return this.agencySecurityDeposit;
	}

	public void setAgencySecurityDeposit(BigDecimal agencySecurityDeposit) {
		this.agencySecurityDeposit = agencySecurityDeposit;
	}

	public Date getAgencyStartDate() {
		return this.agencyStartDate;
	}

	public void setAgencyStartDate(Date agencyStartDate) {
		this.agencyStartDate = agencyStartDate;
	}

	public Timestamp getPdCreationTimestamp() {
		return this.pdCreationTimestamp;
	}

	public void setPdCreationTimestamp(Timestamp pdCreationTimestamp) {
		this.pdCreationTimestamp = pdCreationTimestamp;
	}

	public Timestamp getPdModificationTimestamp() {
		return this.pdModificationTimestamp;
	}

	public void setPdModificationTimestamp(Timestamp pdModificationTimestamp) {
		this.pdModificationTimestamp = pdModificationTimestamp;
	}

	public String getProductCompany() {
		return this.productCompany;
	}

	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<Inventorydetail> getInventorydetails() {
		return this.inventorydetails;
	}

	public void setInventorydetails(List<Inventorydetail> inventorydetails) {
		this.inventorydetails = inventorydetails;
	}

	public Inventorydetail addInventorydetail(Inventorydetail inventorydetail) {
		getInventorydetails().add(inventorydetail);
		inventorydetail.setProductdetail(this);

		return inventorydetail;
	}

	public Inventorydetail removeInventorydetail(Inventorydetail inventorydetail) {
		getInventorydetails().remove(inventorydetail);
		inventorydetail.setProductdetail(null);

		return inventorydetail;
	}

	public List<Inventoryrefilldetail> getInventoryrefilldetails() {
		return this.inventoryrefilldetails;
	}

	public void setInventoryrefilldetails(List<Inventoryrefilldetail> inventoryrefilldetails) {
		this.inventoryrefilldetails = inventoryrefilldetails;
	}

	public Inventoryrefilldetail addInventoryrefilldetail(Inventoryrefilldetail inventoryrefilldetail) {
		getInventoryrefilldetails().add(inventoryrefilldetail);
		inventoryrefilldetail.setProductdetail(this);

		return inventoryrefilldetail;
	}

	public Inventoryrefilldetail removeInventoryrefilldetail(Inventoryrefilldetail inventoryrefilldetail) {
		getInventoryrefilldetails().remove(inventoryrefilldetail);
		inventoryrefilldetail.setProductdetail(null);

		return inventoryrefilldetail;
	}

	public List<Invoiceitemdetail> getInvoiceitemdetails() {
		return this.invoiceitemdetails;
	}

	public void setInvoiceitemdetails(List<Invoiceitemdetail> invoiceitemdetails) {
		this.invoiceitemdetails = invoiceitemdetails;
	}

	public Invoiceitemdetail addInvoiceitemdetail(Invoiceitemdetail invoiceitemdetail) {
		getInvoiceitemdetails().add(invoiceitemdetail);
		invoiceitemdetail.setProductdetail(this);

		return invoiceitemdetail;
	}

	public Invoiceitemdetail removeInvoiceitemdetail(Invoiceitemdetail invoiceitemdetail) {
		getInvoiceitemdetails().remove(invoiceitemdetail);
		invoiceitemdetail.setProductdetail(null);

		return invoiceitemdetail;
	}
	
	public HSN getProductHSN() {
		return productHSN;
	}

	public void setProductHSN(HSN productHSN) {
		this.productHSN = productHSN;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductTaxRate() {
		return productTaxRate;
	}

	public void setProductTaxRate(float productTaxRate) {
		this.productTaxRate = productTaxRate;
	}


	public String getProductServiceOrGood() {
		return productServiceOrGood;
	}

	public void setProductServiceOrGood(String productServiceOrGood) {
		this.productServiceOrGood = productServiceOrGood;
	}

	public SacMaster getProductSac() {
		return productSac;
	}

	public void setProductSac(SacMaster productSac) {
		this.productSac = productSac;
	}

}