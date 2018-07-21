package com.prompt.marginplus.models;

public class StateModel {


	private String statename;
	
	private String statecode;

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	
	@Override
	public String toString() {
		return "StateModel [statename=" + statename + ", statecode=" + statecode + "]";
	}
	
	
}
