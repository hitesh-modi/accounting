package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the servicemaintainencedetails database table.
 * 
 */
@Entity
@Table(name="servicemaintainencedetails")
@NamedQuery(name="Servicemaintainencedetail.findAll", query="SELECT s FROM Servicemaintainencedetail s")
public class Servicemaintainencedetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SMD_Id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="SMD_CREATION_TIMESTAMP")
	private Timestamp smdCreationTimestamp;

	@Column(name="SMD_MODIFICATION_TIMESTAMP")
	private Timestamp smdModificationTimestamp;

	private BigDecimal SMD_ServiceMaintainenceAmount;

	@Temporal(TemporalType.DATE)
	private Date SMD_ServiceMaintainenceDate;

	private String SMD_ServiceMaintainenceType;

	//bi-directional many-to-one association to Servicedetail
	@ManyToOne
	@JoinColumn(name="SMD_ServiceId")
	private Servicedetail servicedetail;

	public Servicemaintainencedetail() {
	}

	public Timestamp getSmdCreationTimestamp() {
		return this.smdCreationTimestamp;
	}

	public void setSmdCreationTimestamp(Timestamp smdCreationTimestamp) {
		this.smdCreationTimestamp = smdCreationTimestamp;
	}

	public Timestamp getSmdModificationTimestamp() {
		return this.smdModificationTimestamp;
	}

	public void setSmdModificationTimestamp(Timestamp smdModificationTimestamp) {
		this.smdModificationTimestamp = smdModificationTimestamp;
	}

	public BigDecimal getSMD_ServiceMaintainenceAmount() {
		return this.SMD_ServiceMaintainenceAmount;
	}

	public void setSMD_ServiceMaintainenceAmount(BigDecimal SMD_ServiceMaintainenceAmount) {
		this.SMD_ServiceMaintainenceAmount = SMD_ServiceMaintainenceAmount;
	}

	public Date getSMD_ServiceMaintainenceDate() {
		return this.SMD_ServiceMaintainenceDate;
	}

	public void setSMD_ServiceMaintainenceDate(Date SMD_ServiceMaintainenceDate) {
		this.SMD_ServiceMaintainenceDate = SMD_ServiceMaintainenceDate;
	}

	public String getSMD_ServiceMaintainenceType() {
		return this.SMD_ServiceMaintainenceType;
	}

	public void setSMD_ServiceMaintainenceType(String SMD_ServiceMaintainenceType) {
		this.SMD_ServiceMaintainenceType = SMD_ServiceMaintainenceType;
	}

	public Servicedetail getServicedetail() {
		return this.servicedetail;
	}

	public void setServicedetail(Servicedetail servicedetail) {
		this.servicedetail = servicedetail;
	}

}