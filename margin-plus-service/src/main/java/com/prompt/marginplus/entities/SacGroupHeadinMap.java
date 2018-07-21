package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sac_group_headin_map database table.
 * 
 */
@Entity
@Table(name="sac_group_headin_map")
@NamedQuery(name="SacGroupHeadinMap.findAll", query="SELECT s FROM SacGroupHeadinMap s")
public class SacGroupHeadinMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="group_id")
	private String groupId;

	//bi-directional many-to-one association to SacHeadingMaster
	@ManyToOne
	@JoinColumn(name="heading_id")
	private SacHeadingMaster sacHeadingMaster;

	//bi-directional one-to-one association to SacGroupMaster
	@OneToOne
	@JoinColumn(name="group_id")
	private SacGroupMaster sacGroupMaster;

	public SacGroupHeadinMap() {
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public SacHeadingMaster getSacHeadingMaster() {
		return this.sacHeadingMaster;
	}

	public void setSacHeadingMaster(SacHeadingMaster sacHeadingMaster) {
		this.sacHeadingMaster = sacHeadingMaster;
	}

	public SacGroupMaster getSacGroupMaster() {
		return this.sacGroupMaster;
	}

	public void setSacGroupMaster(SacGroupMaster sacGroupMaster) {
		this.sacGroupMaster = sacGroupMaster;
	}

}