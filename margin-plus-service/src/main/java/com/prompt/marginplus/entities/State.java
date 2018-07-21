package com.prompt.marginplus.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the states database table.
 * 
 */
@Entity
@Table(name="states")
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String statecode;

	private String stateinitial;

	private String statename;

	private String statetype;

	public State() {
	}

	public String getStatecode() {
		return this.statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getStateinitial() {
		return this.stateinitial;
	}

	public void setStateinitial(String stateinitial) {
		this.stateinitial = stateinitial;
	}

	public String getStatename() {
		return this.statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getStatetype() {
		return this.statetype;
	}

	public void setStatetype(String statetype) {
		this.statetype = statetype;
	}

}