package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sac_accountingcode_group_map database table.
 * 
 */
@Entity
@Table(name="sac_accountingcode_group_map")
@NamedQuery(name="SacAccountingcodeGroupMap.findAll", query="SELECT s FROM SacAccountingcodeGroupMap s")
public class SacAccountingcodeGroupMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String sac;

	//bi-directional one-to-one association to SacMaster
	@OneToOne
	@JoinColumn(name="sac")
	private SacMaster sacMaster;

	//bi-directional many-to-one association to SacGroupMaster
	@ManyToOne
	@JoinColumn(name="group_id")
	private SacGroupMaster sacGroupMaster;

	public SacAccountingcodeGroupMap() {
	}

	public String getSac() {
		return this.sac;
	}

	public void setSac(String sac) {
		this.sac = sac;
	}

	public SacMaster getSacMaster() {
		return this.sacMaster;
	}

	public void setSacMaster(SacMaster sacMaster) {
		this.sacMaster = sacMaster;
	}

	public SacGroupMaster getSacGroupMaster() {
		return this.sacGroupMaster;
	}

	public void setSacGroupMaster(SacGroupMaster sacGroupMaster) {
		this.sacGroupMaster = sacGroupMaster;
	}

}