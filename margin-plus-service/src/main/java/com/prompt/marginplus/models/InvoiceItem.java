package com.prompt.marginplus.models;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by Hitesh Modi on 19-11-2017.
 */
public class InvoiceItem {
    private int serialNumber;
    private Product product;
    private int quantity;
    private String unit;
    private BigDecimal rate;
    private BigDecimal total;
    private BigDecimal taxableValue;
    private BigDecimal cgstRate = new BigDecimal(0);
    private BigDecimal cgstAmount = new BigDecimal(0);
    private BigDecimal sgstRate = new BigDecimal(0);;
    private BigDecimal sgstAmount = new BigDecimal(0);;
    private BigDecimal igstAmount = new BigDecimal(0);;
    private BigDecimal igstRate = new BigDecimal(0);;
    private BigDecimal discount;
    private Collection<TaxItem> additionalTaxes;

    public Collection<TaxItem> getAdditionalTaxes() {
        return additionalTaxes;
    }

    public void setAdditionalTaxes(Collection<TaxItem> additionalTaxes) {
        this.additionalTaxes = additionalTaxes;
    }

    public BigDecimal getCgstRate() {
        return cgstRate;
    }

    public void setCgstRate(BigDecimal cgstRate) {
        this.cgstRate = cgstRate;
    }

    public BigDecimal getCgstAmount() {
        return cgstAmount;
    }

    public void setCgstAmount(BigDecimal cgstAmount) {
        this.cgstAmount = cgstAmount;
    }

    public BigDecimal getSgstRate() {
        return sgstRate;
    }

    public void setSgstRate(BigDecimal sgstRate) {
        this.sgstRate = sgstRate;
    }

    public BigDecimal getSgstAmount() {
        return sgstAmount;
    }

    public void setSgstAmount(BigDecimal sgstAmount) {
        this.sgstAmount = sgstAmount;
    }

    public BigDecimal getIgstAmount() {
        return igstAmount;
    }

    public void setIgstAmount(BigDecimal igstAmount) {
        this.igstAmount = igstAmount;
    }

    public BigDecimal getIgstRate() {
        return igstRate;
    }

    public void setIgstRate(BigDecimal igstRate) {
        this.igstRate = igstRate;
    }

    private Collection<TaxItem> taxes;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal amount) {
        this.total = amount;
    }

    public BigDecimal getTaxableValue() {
        return taxableValue;
    }

    public void setTaxableValue(BigDecimal taxableValue) {
        this.taxableValue = taxableValue;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Collection<TaxItem> getTaxes() {
        return taxes;
    }

    public void setTaxes(Collection<TaxItem> taxes) {
        this.taxes = taxes;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "serialNumber=" + serialNumber +
                ", product=" + product +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", rate=" + rate +
                ", total=" + total +
                ", taxableValue=" + taxableValue +
                ", cgstRate=" + cgstRate +
                ", cgstAmount=" + cgstAmount +
                ", sgstRate=" + sgstRate +
                ", sgstAmount=" + sgstAmount +
                ", igstAmount=" + igstAmount +
                ", igstRate=" + igstRate +
                ", discount=" + discount +
                ", additionalTaxes=" + additionalTaxes +
                ", taxes=" + taxes +
                '}';
    }
}
