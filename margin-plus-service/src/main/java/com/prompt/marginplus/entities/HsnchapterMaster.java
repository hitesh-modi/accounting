package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hsnchapter_master database table.
 * 
 */
@Entity
@Table(name="hsnchapter_master")
@NamedQuery(name="HsnchapterMaster.findAll", query="SELECT h FROM HsnchapterMaster h")
public class HsnchapterMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="hsnchapter_id")
	private String hsnchapterId;

	@Column(name="hsnchapter_desc")
	private String hsnchapterDesc;

	//bi-directional one-to-one association to ChapterSectionMap
	@OneToOne(mappedBy="hsnchapterMaster")
	private ChapterSectionMap chapterSectionMap;

	//bi-directional many-to-one association to HsnChapterMap
	@OneToMany(mappedBy="hsnchapterMaster")
	private List<HsnChapterMap> hsnChapterMaps;

	public HsnchapterMaster() {
	}

	public String getHsnchapterId() {
		return this.hsnchapterId;
	}

	public void setHsnchapterId(String hsnchapterId) {
		this.hsnchapterId = hsnchapterId;
	}

	public String getHsnchapterDesc() {
		return this.hsnchapterDesc;
	}

	public void setHsnchapterDesc(String hsnchapterDesc) {
		this.hsnchapterDesc = hsnchapterDesc;
	}

	public ChapterSectionMap getChapterSectionMap() {
		return this.chapterSectionMap;
	}

	public void setChapterSectionMap(ChapterSectionMap chapterSectionMap) {
		this.chapterSectionMap = chapterSectionMap;
	}

	public List<HsnChapterMap> getHsnChapterMaps() {
		return this.hsnChapterMaps;
	}

	public void setHsnChapterMaps(List<HsnChapterMap> hsnChapterMaps) {
		this.hsnChapterMaps = hsnChapterMaps;
	}

	public HsnChapterMap addHsnChapterMap(HsnChapterMap hsnChapterMap) {
		getHsnChapterMaps().add(hsnChapterMap);
		hsnChapterMap.setHsnchapterMaster(this);

		return hsnChapterMap;
	}

	public HsnChapterMap removeHsnChapterMap(HsnChapterMap hsnChapterMap) {
		getHsnChapterMaps().remove(hsnChapterMap);
		hsnChapterMap.setHsnchapterMaster(null);

		return hsnChapterMap;
	}

}