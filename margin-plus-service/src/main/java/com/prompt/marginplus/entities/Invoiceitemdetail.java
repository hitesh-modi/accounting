package com.prompt.marginplus.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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


/**
 * The persistent class for the invoiceitemdetails database table.
 * 
 */
@Entity
@Table(name="invoiceitemdetails")
@NamedQuery(name="Invoiceitemdetail.findAll", query="SELECT i FROM Invoiceitemdetail i")
public class Invoiceitemdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="iid_itemid")
	private String IID_ItemId;

	@Column(name="iid_itemdiscount")
	private BigDecimal IID_ItemDiscount;

	@Column(name="iid_itemprice")
	private BigDecimal IID_ItemPrice;

	@Column(name="iid_itemunit")
	private String IID_ItemUnit;
	
	@Column(name="iid_itemtotalamount")
	private BigDecimal IID_ItemTotalAmount;
	
	@Column(name="iid_productquantity")
	private int IID_ProductQuantity;

	@Column(name="iid_taxableamount")
	private BigDecimal iidTaxableamount;

	//bi-directional many-to-one association to Invoicedetail
	@ManyToOne
	@JoinColumn(name="iid_invoiceid")
	private Invoicedetail invoicedetail;

	//bi-directional many-to-one association to Productdetail
	@ManyToOne
	@JoinColumn(name="iid_productid")
	private Productdetail productdetail;

	//bi-directional many-to-one association to Invoiceitemtaxdetail
	@OneToMany(mappedBy="invoiceitemdetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Invoiceitemtaxdetail> invoiceitemtaxdetails;

	public Invoiceitemdetail() {
	}

	public String getIID_ItemId() {
		return this.IID_ItemId;
	}

	public void setIID_ItemId(String IID_ItemId) {
		this.IID_ItemId = IID_ItemId;
	}

	public BigDecimal getIID_ItemDiscount() {
		return this.IID_ItemDiscount;
	}

	public void setIID_ItemDiscount(BigDecimal IID_ItemDiscount) {
		this.IID_ItemDiscount = IID_ItemDiscount;
	}

	public BigDecimal getIID_ItemPrice() {
		return this.IID_ItemPrice;
	}

	public void setIID_ItemPrice(BigDecimal IID_ItemPrice) {
		this.IID_ItemPrice = IID_ItemPrice;
	}


	public int getIID_ProductQuantity() {
		return this.IID_ProductQuantity;
	}

	public void setIID_ProductQuantity(int IID_ProductQuantity) {
		this.IID_ProductQuantity = IID_ProductQuantity;
	}

	public BigDecimal getIidTaxableamount() {
		return this.iidTaxableamount;
	}

	public void setIidTaxableamount(BigDecimal iidTaxableamount) {
		this.iidTaxableamount = iidTaxableamount;
	}

	public Invoicedetail getInvoicedetail() {
		return this.invoicedetail;
	}

	public void setInvoicedetail(Invoicedetail invoicedetail) {
		this.invoicedetail = invoicedetail;
	}

	public Productdetail getProductdetail() {
		return this.productdetail;
	}

	public void setProductdetail(Productdetail productdetail) {
		this.productdetail = productdetail;
	}


	public Set<Invoiceitemtaxdetail> getInvoiceitemtaxdetails() {
		return this.invoiceitemtaxdetails;
	}

	public void setInvoiceitemtaxdetails(Set<Invoiceitemtaxdetail> invoiceitemtaxdetails) {
		this.invoiceitemtaxdetails = invoiceitemtaxdetails;
	}

	public Invoiceitemtaxdetail addInvoiceitemtaxdetail(Invoiceitemtaxdetail invoiceitemtaxdetail) {
		getInvoiceitemtaxdetails().add(invoiceitemtaxdetail);
		invoiceitemtaxdetail.setInvoiceitemdetail(this);

		return invoiceitemtaxdetail;
	}

	public Invoiceitemtaxdetail removeInvoiceitemtaxdetail(Invoiceitemtaxdetail invoiceitemtaxdetail) {
		getInvoiceitemtaxdetails().remove(invoiceitemtaxdetail);
		invoiceitemtaxdetail.setInvoiceitemdetail(null);

		return invoiceitemtaxdetail;
	}
	
	public String getIID_ItemUnit() {
		return IID_ItemUnit;
	}

	public void setIID_ItemUnit(String iID_ItemUnit) {
		IID_ItemUnit = iID_ItemUnit;
	}
	
	public BigDecimal getIID_ItemTotalAmount() {
		return IID_ItemTotalAmount;
	}

	public void setIID_ItemTotalAmount(BigDecimal iID_ItemTotalAmount) {
		IID_ItemTotalAmount = iID_ItemTotalAmount;
	}

}