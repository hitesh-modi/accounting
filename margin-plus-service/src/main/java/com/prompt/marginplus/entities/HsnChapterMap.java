package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the hsn_chapter_map database table.
 * 
 */
@Entity
@Table(name="hsn_chapter_map")
@NamedQuery(name="HsnChapterMap.findAll", query="SELECT h FROM HsnChapterMap h")
public class HsnChapterMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String hsncode;

	//bi-directional one-to-one association to Hsnmaster
	@OneToOne
	@JoinColumn(name="hsncode")
	private Hsnmaster hsnmaster;

	//bi-directional many-to-one association to HsnchapterMaster
	@ManyToOne
	@JoinColumn(name="chapter_id")
	private HsnchapterMaster hsnchapterMaster;

	public HsnChapterMap() {
	}

	public String getHsncode() {
		return this.hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}

	public Hsnmaster getHsnmaster() {
		return this.hsnmaster;
	}

	public void setHsnmaster(Hsnmaster hsnmaster) {
		this.hsnmaster = hsnmaster;
	}

	public HsnchapterMaster getHsnchapterMaster() {
		return this.hsnchapterMaster;
	}

	public void setHsnchapterMaster(HsnchapterMaster hsnchapterMaster) {
		this.hsnchapterMaster = hsnchapterMaster;
	}

}