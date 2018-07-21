package com.prompt.marginplus.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Hitesh Modi on 02-03-2018.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExpenseType {
    RENT(1, "Rent"),
    UTILITIES(2, "Utilities"),
    WAGES(3, "Wages"),
    TAXES(4, "Taxes"),
    STATIONARY(5, "Stationary"),
    PURCHASES(6, "Purchases"),
    TRAVEL(7, "Travel"),
    LOGISTICS(8, "Logistics"),
    MAINTENANCE(9, "Maintenance");

    private String type;

    private int code;

    @JsonCreator
    public static ProductType fromNode(JsonNode node) {
        return ProductType.valueOf(node.asText().toUpperCase());
    }


    private ExpenseType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    @JsonProperty
    public String getType() {
        return this.type;
    }

    @JsonProperty
    public Integer getCode() {
        return this.code;
    }

}
