package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the hsnmaster database table.
 * 
 */
@Entity
@NamedQuery(name="Hsnmaster.findAll", query="SELECT h FROM Hsnmaster h")
public class Hsnmaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String hsncode;

	private String hsndesc;

	//bi-directional one-to-one association to HsnChapterMap
	@OneToOne(mappedBy="hsnmaster")
	private HsnChapterMap hsnChapterMap;

	public Hsnmaster() {
	}

	public String getHsncode() {
		return this.hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}

	public String getHsndesc() {
		return this.hsndesc;
	}

	public void setHsndesc(String hsndesc) {
		this.hsndesc = hsndesc;
	}

	public HsnChapterMap getHsnChapterMap() {
		return this.hsnChapterMap;
	}

	public void setHsnChapterMap(HsnChapterMap hsnChapterMap) {
		this.hsnChapterMap = hsnChapterMap;
	}

}