package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the section_master database table.
 * 
 */
@Entity
@Table(name="section_master")
@NamedQuery(name="SectionMaster.findAll", query="SELECT s FROM SectionMaster s")
public class SectionMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="section_id")
	private String sectionId;

	@Column(name="section_desc")
	private String sectionDesc;

	//bi-directional many-to-one association to ChapterSectionMap
	@OneToMany(mappedBy="sectionMaster")
	private List<ChapterSectionMap> chapterSectionMaps;

	public SectionMaster() {
	}

	public String getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionDesc() {
		return this.sectionDesc;
	}

	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}

	public List<ChapterSectionMap> getChapterSectionMaps() {
		return this.chapterSectionMaps;
	}

	public void setChapterSectionMaps(List<ChapterSectionMap> chapterSectionMaps) {
		this.chapterSectionMaps = chapterSectionMaps;
	}

	public ChapterSectionMap addChapterSectionMap(ChapterSectionMap chapterSectionMap) {
		getChapterSectionMaps().add(chapterSectionMap);
		chapterSectionMap.setSectionMaster(this);

		return chapterSectionMap;
	}

	public ChapterSectionMap removeChapterSectionMap(ChapterSectionMap chapterSectionMap) {
		getChapterSectionMaps().remove(chapterSectionMap);
		chapterSectionMap.setSectionMaster(null);

		return chapterSectionMap;
	}

}