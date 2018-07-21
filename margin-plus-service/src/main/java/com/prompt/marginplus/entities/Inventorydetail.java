package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inventorydetails database table.
 * 
 */
@Entity
@Table(name="inventorydetails")
@NamedQuery(name="Inventorydetail.findAll", query="SELECT i FROM Inventorydetail i")
public class Inventorydetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int ID_ProductQuantity;

	//bi-directional many-to-one association to Productdetail
	@ManyToOne
	@JoinColumn(name="ID_ProductID")
	private Productdetail productdetail;

	public Inventorydetail() {
	}

	public int getID_ProductQuantity() {
		return this.ID_ProductQuantity;
	}

	public void setID_ProductQuantity(int ID_ProductQuantity) {
		this.ID_ProductQuantity = ID_ProductQuantity;
	}

	public Productdetail getProductdetail() {
		return this.productdetail;
	}

	public void setProductdetail(Productdetail productdetail) {
		this.productdetail = productdetail;
	}

}