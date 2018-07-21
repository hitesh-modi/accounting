package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sac_group_master database table.
 * 
 */
@Entity
@Table(name="sac_group_master")
@NamedQuery(name="SacGroupMaster.findAll", query="SELECT s FROM SacGroupMaster s")
public class SacGroupMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="group_id")
	private String groupId;

	@Column(name="group_desc")
	private String groupDesc;

	//bi-directional many-to-one association to SacAccountingcodeGroupMap
	@OneToMany(mappedBy="sacGroupMaster")
	private List<SacAccountingcodeGroupMap> sacAccountingcodeGroupMaps;

	//bi-directional one-to-one association to SacGroupHeadinMap
	@OneToOne(mappedBy="sacGroupMaster")
	private SacGroupHeadinMap sacGroupHeadinMap;

	public SacGroupMaster() {
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupDesc() {
		return this.groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public List<SacAccountingcodeGroupMap> getSacAccountingcodeGroupMaps() {
		return this.sacAccountingcodeGroupMaps;
	}

	public void setSacAccountingcodeGroupMaps(List<SacAccountingcodeGroupMap> sacAccountingcodeGroupMaps) {
		this.sacAccountingcodeGroupMaps = sacAccountingcodeGroupMaps;
	}

	public SacAccountingcodeGroupMap addSacAccountingcodeGroupMap(SacAccountingcodeGroupMap sacAccountingcodeGroupMap) {
		getSacAccountingcodeGroupMaps().add(sacAccountingcodeGroupMap);
		sacAccountingcodeGroupMap.setSacGroupMaster(this);

		return sacAccountingcodeGroupMap;
	}

	public SacAccountingcodeGroupMap removeSacAccountingcodeGroupMap(SacAccountingcodeGroupMap sacAccountingcodeGroupMap) {
		getSacAccountingcodeGroupMaps().remove(sacAccountingcodeGroupMap);
		sacAccountingcodeGroupMap.setSacGroupMaster(null);

		return sacAccountingcodeGroupMap;
	}

	public SacGroupHeadinMap getSacGroupHeadinMap() {
		return this.sacGroupHeadinMap;
	}

	public void setSacGroupHeadinMap(SacGroupHeadinMap sacGroupHeadinMap) {
		this.sacGroupHeadinMap = sacGroupHeadinMap;
	}

}