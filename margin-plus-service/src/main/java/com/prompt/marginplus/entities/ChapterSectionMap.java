package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chapter_section_map database table.
 * 
 */
@Entity
@Table(name="chapter_section_map")
@NamedQuery(name="ChapterSectionMap.findAll", query="SELECT c FROM ChapterSectionMap c")
public class ChapterSectionMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="chapter_id")
	private String chapterId;

	//bi-directional many-to-one association to SectionMaster
	@ManyToOne
	@JoinColumn(name="section_id")
	private SectionMaster sectionMaster;

	//bi-directional one-to-one association to HsnchapterMaster
	@OneToOne
	@JoinColumn(name="chapter_id")
	private HsnchapterMaster hsnchapterMaster;

	public ChapterSectionMap() {
	}

	public String getChapterId() {
		return this.chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public SectionMaster getSectionMaster() {
		return this.sectionMaster;
	}

	public void setSectionMaster(SectionMaster sectionMaster) {
		this.sectionMaster = sectionMaster;
	}

	public HsnchapterMaster getHsnchapterMaster() {
		return this.hsnchapterMaster;
	}

	public void setHsnchapterMaster(HsnchapterMaster hsnchapterMaster) {
		this.hsnchapterMaster = hsnchapterMaster;
	}

}