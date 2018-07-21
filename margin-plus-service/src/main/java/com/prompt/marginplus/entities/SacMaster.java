package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sac_master database table.
 * 
 */
@Entity
@Table(name="sac_master")
@NamedQuery(name="SacMaster.findAll", query="SELECT s FROM SacMaster s")
public class SacMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="sac_id")
	private String sacId;

	@Column(name="sac_desc")
	private String sacDesc;

	//bi-directional one-to-one association to SacAccountingcodeGroupMap
	@OneToOne(mappedBy="sacMaster")
	private SacAccountingcodeGroupMap sacAccountingcodeGroupMap;

	public SacMaster() {
	}

	public String getSacId() {
		return this.sacId;
	}

	public void setSacId(String sacId) {
		this.sacId = sacId;
	}

	public String getSacDesc() {
		return this.sacDesc;
	}

	public void setSacDesc(String sacDesc) {
		this.sacDesc = sacDesc;
	}

	public SacAccountingcodeGroupMap getSacAccountingcodeGroupMap() {
		return this.sacAccountingcodeGroupMap;
	}

	public void setSacAccountingcodeGroupMap(SacAccountingcodeGroupMap sacAccountingcodeGroupMap) {
		this.sacAccountingcodeGroupMap = sacAccountingcodeGroupMap;
	}

}