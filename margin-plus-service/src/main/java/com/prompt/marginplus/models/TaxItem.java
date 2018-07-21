package com.prompt.marginplus.models;

import com.prompt.marginplus.types.TaxType;

import java.math.BigDecimal;

/**
 * Created by Hitesh on 19-11-2017.
 */
public class TaxItem {
    private TaxType type;
    private BigDecimal rate;
    private BigDecimal amount;

    public TaxType getType() {
        return type;
    }

    public void setType(TaxType type) {
        this.type = type;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal taxRate) {
        this.rate = taxRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TaxItem{" +
                "type=" + type +
                ", rate=" + rate +
                ", amount=" + amount +
                '}';
    }
}
