package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sac_heading_master database table.
 * 
 */
@Entity
@Table(name="sac_heading_master")
@NamedQuery(name="SacHeadingMaster.findAll", query="SELECT s FROM SacHeadingMaster s")
public class SacHeadingMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="heading_id")
	private String headingId;

	@Column(name="heading_desc")
	private String headingDesc;

	//bi-directional many-to-one association to SacGroupHeadinMap
	@OneToMany(mappedBy="sacHeadingMaster")
	private List<SacGroupHeadinMap> sacGroupHeadinMaps;

	public SacHeadingMaster() {
	}

	public String getHeadingId() {
		return this.headingId;
	}

	public void setHeadingId(String headingId) {
		this.headingId = headingId;
	}

	public String getHeadingDesc() {
		return this.headingDesc;
	}

	public void setHeadingDesc(String headingDesc) {
		this.headingDesc = headingDesc;
	}

	public List<SacGroupHeadinMap> getSacGroupHeadinMaps() {
		return this.sacGroupHeadinMaps;
	}

	public void setSacGroupHeadinMaps(List<SacGroupHeadinMap> sacGroupHeadinMaps) {
		this.sacGroupHeadinMaps = sacGroupHeadinMaps;
	}

	public SacGroupHeadinMap addSacGroupHeadinMap(SacGroupHeadinMap sacGroupHeadinMap) {
		getSacGroupHeadinMaps().add(sacGroupHeadinMap);
		sacGroupHeadinMap.setSacHeadingMaster(this);

		return sacGroupHeadinMap;
	}

	public SacGroupHeadinMap removeSacGroupHeadinMap(SacGroupHeadinMap sacGroupHeadinMap) {
		getSacGroupHeadinMaps().remove(sacGroupHeadinMap);
		sacGroupHeadinMap.setSacHeadingMaster(null);

		return sacGroupHeadinMap;
	}

}