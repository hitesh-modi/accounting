package com.prompt.marginplus.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProductType {
	CEMENT("cement"),
	PUTTY("putty"),
	PAINT("paint"),
	TILES("tiles");
	
	private String value;
	
	private ProductType(String value) {
		this.value = value;
	}
	
	 @JsonCreator
	    public static ProductType fromNode(JsonNode node) {
	        return ProductType.valueOf(node.asText().toUpperCase());
	    }
	
	 @JsonProperty
	public String getType() {
		return this.value;
	}
}
