package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the invoiceitemtaxdetails database table.
 * 
 */
@Entity
@Table(name="invoiceitemtaxdetails")
@NamedQuery(name="Invoiceitemtaxdetail.findAll", query="SELECT i FROM Invoiceitemtaxdetail i")
public class Invoiceitemtaxdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="itd_itemid")
	private String ITD_itemid;

	@Column(name="itd_taxtype")
	private String ITD_taxType;
	
	@Column(name="itd_taxamount")
	private BigDecimal ITD_taxamount;

	@Column(name="itd_taxrate")
	private BigDecimal ITD_taxrate;
	
	//bi-directional many-to-one association to Invoiceitemdetail
	@ManyToOne
	@JoinColumn(name="itd_invoiceitemid")
	private Invoiceitemdetail invoiceitemdetail;


	public Invoiceitemtaxdetail() {
	}


	public String getITD_invoicetaxid() {
		return ITD_itemid;
	}


	public void setITD_invoicetaxid(String iTD_invoicetaxid) {
		ITD_itemid = iTD_invoicetaxid;
	}


	public String getITD_taxType() {
		return ITD_taxType;
	}


	public void setITD_taxType(String iTD_taxType) {
		ITD_taxType = iTD_taxType;
	}


	public BigDecimal getITD_taxamount() {
		return this.ITD_taxamount;
	}

	public void setITD_taxamount(BigDecimal ITD_taxamount) {
		this.ITD_taxamount = ITD_taxamount;
	}

	public BigDecimal getITD_taxrate() {
		return this.ITD_taxrate;
	}

	public void setITD_taxrate(BigDecimal ITD_taxrate) {
		this.ITD_taxrate = ITD_taxrate;
	}

	public Invoiceitemdetail getInvoiceitemdetail() {
		return this.invoiceitemdetail;
	}

	public void setInvoiceitemdetail(Invoiceitemdetail invoiceitemdetail) {
		this.invoiceitemdetail = invoiceitemdetail;
	}
	
}