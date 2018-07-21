package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the servicerecords database table.
 * 
 */
@Entity
@Table(name="servicerecords")
@NamedQuery(name="Servicerecord.findAll", query="SELECT s FROM Servicerecord s")
public class Servicerecord implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SR_Id")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Timestamp SR_Creation_timestamp;

	private BigDecimal SR_EndReading;

	private BigDecimal SR_Kilometers;

	private Timestamp SR_modification_timestamp;

	private int SR_NumberOfTrips;

	@Temporal(TemporalType.DATE)
	private Date SR_ServiceDate;

	private BigDecimal SR_StartReading;

	//bi-directional many-to-one association to Servicedetail
	@ManyToOne
	@JoinColumn(name="SR_ServiceId")
	private Servicedetail servicedetail;

	public Servicerecord() {
	}

	public Timestamp getSR_Creation_timestamp() {
		return this.SR_Creation_timestamp;
	}

	public void setSR_Creation_timestamp(Timestamp SR_Creation_timestamp) {
		this.SR_Creation_timestamp = SR_Creation_timestamp;
	}

	public BigDecimal getSR_EndReading() {
		return this.SR_EndReading;
	}

	public void setSR_EndReading(BigDecimal SR_EndReading) {
		this.SR_EndReading = SR_EndReading;
	}

	public BigDecimal getSR_Kilometers() {
		return this.SR_Kilometers;
	}

	public void setSR_Kilometers(BigDecimal SR_Kilometers) {
		this.SR_Kilometers = SR_Kilometers;
	}

	public Timestamp getSR_modification_timestamp() {
		return this.SR_modification_timestamp;
	}

	public void setSR_modification_timestamp(Timestamp SR_modification_timestamp) {
		this.SR_modification_timestamp = SR_modification_timestamp;
	}

	public int getSR_NumberOfTrips() {
		return this.SR_NumberOfTrips;
	}

	public void setSR_NumberOfTrips(int SR_NumberOfTrips) {
		this.SR_NumberOfTrips = SR_NumberOfTrips;
	}

	public Date getSR_ServiceDate() {
		return this.SR_ServiceDate;
	}

	public void setSR_ServiceDate(Date SR_ServiceDate) {
		this.SR_ServiceDate = SR_ServiceDate;
	}

	public BigDecimal getSR_StartReading() {
		return this.SR_StartReading;
	}

	public void setSR_StartReading(BigDecimal SR_StartReading) {
		this.SR_StartReading = SR_StartReading;
	}

	public Servicedetail getServicedetail() {
		return this.servicedetail;
	}

	public void setServicedetail(Servicedetail servicedetail) {
		this.servicedetail = servicedetail;
	}

}