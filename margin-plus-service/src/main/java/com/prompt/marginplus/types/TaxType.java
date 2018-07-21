package com.prompt.marginplus.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by Hitesh on 19-11-2017.
 */
public enum TaxType {

    SERVICE_TAX("Service Tax"),
    EXCISE_DUTY("Excise Duty"),
    VAT("VAT"),
    CGST("Central GST"),
    SGST("State GST"),
    IGST("Integrated GST");

    private String taxType;

    private TaxType(String taxType) {
        this.taxType = taxType;
    }

    @JsonValue
    public String getTaxType() {
        return taxType;
    }

    @JsonCreator
    public static TaxType create(String val) {
        TaxType[] taxTypes = TaxType.values();
        for (TaxType currTaxType:
             taxTypes) {
            if(currTaxType.getTaxType().equalsIgnoreCase(val)) {
                return  currTaxType;
            }
        }

        return SERVICE_TAX;
    }

}
