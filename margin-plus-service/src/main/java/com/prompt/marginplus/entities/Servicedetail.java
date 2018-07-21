package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the servicedetails database table.
 * 
 */
@Entity
@Table(name="servicedetails")
@NamedQuery(name="Servicedetail.findAll", query="SELECT s FROM Servicedetail s")
public class Servicedetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int SD_ServiceId;

	@Column(name="SD_CREATION_TIMESTAMP")
	private Timestamp sdCreationTimestamp;

	@Column(name="SD_MODIFICATION_TIMESTAMP")
	private Timestamp sdModificationTimestamp;

	private String SD_ServiceType;

	@Temporal(TemporalType.DATE)
	private Date SD_StartDate;

	//bi-directional many-to-one association to Servicemaintainencedetail
	@OneToMany(mappedBy="servicedetail")
	private List<Servicemaintainencedetail> servicemaintainencedetails;

	//bi-directional many-to-one association to Servicerecord
	@OneToMany(mappedBy="servicedetail")
	private List<Servicerecord> servicerecords;

	public Servicedetail() {
	}

	public int getSD_ServiceId() {
		return this.SD_ServiceId;
	}

	public void setSD_ServiceId(int SD_ServiceId) {
		this.SD_ServiceId = SD_ServiceId;
	}

	public Timestamp getSdCreationTimestamp() {
		return this.sdCreationTimestamp;
	}

	public void setSdCreationTimestamp(Timestamp sdCreationTimestamp) {
		this.sdCreationTimestamp = sdCreationTimestamp;
	}

	public Timestamp getSdModificationTimestamp() {
		return this.sdModificationTimestamp;
	}

	public void setSdModificationTimestamp(Timestamp sdModificationTimestamp) {
		this.sdModificationTimestamp = sdModificationTimestamp;
	}

	public String getSD_ServiceType() {
		return this.SD_ServiceType;
	}

	public void setSD_ServiceType(String SD_ServiceType) {
		this.SD_ServiceType = SD_ServiceType;
	}

	public Date getSD_StartDate() {
		return this.SD_StartDate;
	}

	public void setSD_StartDate(Date SD_StartDate) {
		this.SD_StartDate = SD_StartDate;
	}


	public List<Servicemaintainencedetail> getServicemaintainencedetails() {
		return this.servicemaintainencedetails;
	}

	public void setServicemaintainencedetails(List<Servicemaintainencedetail> servicemaintainencedetails) {
		this.servicemaintainencedetails = servicemaintainencedetails;
	}

	public Servicemaintainencedetail addServicemaintainencedetail(Servicemaintainencedetail servicemaintainencedetail) {
		getServicemaintainencedetails().add(servicemaintainencedetail);
		servicemaintainencedetail.setServicedetail(this);

		return servicemaintainencedetail;
	}

	public Servicemaintainencedetail removeServicemaintainencedetail(Servicemaintainencedetail servicemaintainencedetail) {
		getServicemaintainencedetails().remove(servicemaintainencedetail);
		servicemaintainencedetail.setServicedetail(null);

		return servicemaintainencedetail;
	}

	public List<Servicerecord> getServicerecords() {
		return this.servicerecords;
	}

	public void setServicerecords(List<Servicerecord> servicerecords) {
		this.servicerecords = servicerecords;
	}

	public Servicerecord addServicerecord(Servicerecord servicerecord) {
		getServicerecords().add(servicerecord);
		servicerecord.setServicedetail(this);

		return servicerecord;
	}

	public Servicerecord removeServicerecord(Servicerecord servicerecord) {
		getServicerecords().remove(servicerecord);
		servicerecord.setServicedetail(null);

		return servicerecord;
	}

}