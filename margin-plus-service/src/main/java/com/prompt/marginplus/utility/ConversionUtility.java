package com.prompt.marginplus.utility;

import java.util.ArrayList;
import java.util.List;

import com.prompt.marginplus.entities.*;
import com.prompt.marginplus.models.*;
import com.prompt.marginplus.types.TaxType;

public class ConversionUtility {

	public static Invoice convertInvoiceEntityToModel(Invoicedetail invoiceEntity) {
		Invoice invoice = new Invoice();
		invoice.setAmountReceived(invoiceEntity.getID_InvoicePaidAmount());
		invoice.setInvoiceDate(invoiceEntity.getID_InvoiceDate());
		invoice.setInvoiceDueDate(invoiceEntity.getID_InvoiceDueDate());
		invoice.setInvoiceNumber(invoiceEntity.getID_InvoiceNumber());
		invoice.setNetTotal(invoiceEntity.getID_GrandTotal());
		invoice.setTotalTax(invoiceEntity.getID_TaxAmount());
		invoice.setGrandTotal(invoiceEntity.getID_InvoiceTotalAmount());
		invoice.setConsignee(convertConsigneeEntityToModel(invoiceEntity.getConsigneeDetail()));
		invoice.setCustomer(convertCustomerEntityToModel(invoiceEntity.getCustomerDetail()));
		
		List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
		int serialNumber = 1;
		for(Invoiceitemdetail invoiceItemEntity : invoiceEntity.getInvoiceitemdetails()) {
			invoiceItems.add(convertInvoiceItemEntityToModel(invoiceItemEntity, serialNumber++));
		}
		
		invoice.setInvoiceItemDetails(invoiceItems);
		
		return invoice;
	}
	
	public static Customer convertCustomerEntityToModel(CustomerDetail customerEntity) {
		Customer customer = new Customer();
		customer.setCustomerId(customerEntity.getCdCustomerId());
		customer.setAddress(customerEntity.getCdCustomerAddress());
		customer.setEmail(customerEntity.getCdCustomerEmail());
		customer.setGstin(customerEntity.getCdCustomerGSTIN());
		customer.setMobileNo(customerEntity.getCdCustomerMobile());
		customer.setName(customerEntity.getCdCustomerName());
		customer.setPhoneNo(customerEntity.getCdCustomerPhone());
		customer.setState(customerEntity.getState().getStatename());
		customer.setStateCode(customerEntity.getState().getStatecode());
		return customer;
	}
	
	public static Consignee convertConsigneeEntityToModel(ConsigneeDetail consigneeEntity) {
		Consignee consignee = new Consignee();
		consignee.setConsigneeId(consigneeEntity.getConsigneeId());
		consignee.setAddress(consigneeEntity.getConsigneeAddress());
		consignee.setEmail(consigneeEntity.getConsigneeEmail());
		consignee.setGstin(consigneeEntity.getConsigneeGSTIN());
		consignee.setMobileNo(consigneeEntity.getConsigneeMobile());
		consignee.setName(consigneeEntity.getConsigneeName());
		consignee.setPhoneNo(consigneeEntity.getConsigneePhone());
		consignee.setState(consigneeEntity.getState().getStatename());
		consignee.setStateCode(consigneeEntity.getState().getStatecode());
		return consignee;
	}
	
	public static InvoiceItem convertInvoiceItemEntityToModel(Invoiceitemdetail invoiceItemEntity, int serialNumber) {
		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setSerialNumber(serialNumber);
		invoiceItem.setProduct(convertProductEntityToModel(invoiceItemEntity.getProductdetail()));
		invoiceItem.setQuantity(invoiceItemEntity.getIID_ProductQuantity());
		invoiceItem.setRate(invoiceItemEntity.getIID_ItemPrice());
		invoiceItem.setTotal(invoiceItemEntity.getIID_ItemTotalAmount());
		invoiceItem.setDiscount(invoiceItemEntity.getIID_ItemDiscount());
        invoiceItem.setTaxableValue(invoiceItemEntity.getIidTaxableamount());
		invoiceItem.setUnit(invoiceItemEntity.getIID_ItemUnit());
		List<TaxItem> taxItems = new ArrayList<>();
		for(Invoiceitemtaxdetail taxDetail: invoiceItemEntity.getInvoiceitemtaxdetails()) {
			TaxItem taxItem = new TaxItem();
			taxItem.setRate(taxDetail.getITD_taxrate());
			taxItem.setAmount(taxDetail.getITD_taxamount());
			taxItem.setType(TaxType.create(taxDetail.getITD_taxType()));
			taxItems.add(taxItem);
			
			if(taxItem.getType().equals(TaxType.CGST)) {
				invoiceItem.setCgstRate(taxItem.getRate());
				invoiceItem.setCgstAmount(taxItem.getAmount());
			}
			else if(taxItem.getType().equals(TaxType.SGST)) {
				invoiceItem.setSgstRate(taxItem.getRate());
				invoiceItem.setSgstAmount(taxItem.getAmount());
			}
			else if(taxItem.getType().equals(TaxType.IGST)) {
				invoiceItem.setIgstRate(taxItem.getRate());
				invoiceItem.setIgstAmount(taxItem.getAmount());
			}
			
		}
		invoiceItem.setTaxes(taxItems);
		return invoiceItem;
	}
	
	public static Product convertProductEntityToModel(Productdetail productEntity) {
		Product product = new Product();
		product.setProductId(productEntity.getProductId());
		product.setType(productEntity.getProductType().toUpperCase());
		product.setAgencyStartDate(productEntity.getAgencyStartDate());
		product.setCompany(productEntity.getProductCompany());
		product.setDepositAmount(productEntity.getAgencySecurityDeposit());
		
		String goodsOrService = productEntity.getProductServiceOrGood();
		
		if(goodsOrService.equalsIgnoreCase("G")) {
			product.setHsnCode(productEntity.getProductHSN().getHsnCode());
			product.setAccountingCodeDesc(productEntity.getProductHSN().getHsnDesc());
		}
		else if(goodsOrService.equalsIgnoreCase("S")) {
			product.setHsnCode(productEntity.getProductSac().getSacId());
			product.setAccountingCodeDesc(productEntity.getProductSac().getSacDesc());
		}
		product.setName(productEntity.getProductName());
		product.setTaxRate(productEntity.getProductTaxRate());
		product.setGood(goodsOrService.equalsIgnoreCase("G")?true:false);
		return product;
	}

	public static Expense convertExpenseEntityToModel(ExpensesEntity entity) {
        Expense expenseModel = new Expense();
        expenseModel.setExpenseAmount(entity.getExpenseamount());
        expenseModel.setExpenseComment(entity.getExpensecomment());
        expenseModel.setExpenseDate(entity.getExpensedate());
        expenseModel.setExpenseId(entity.getExpenseid());
        expenseModel.setExpenseType(entity.getExpensetype());
        return expenseModel;
    }

    public static ExpensesEntity convertExpenseModelToEntity(Expense expense) {
	    ExpensesEntity expenseEntity = new ExpensesEntity();
	    expenseEntity.setExpenseamount(expense.getExpenseAmount());
	    expenseEntity.setExpensedate(expense.getExpenseDate());
	    expenseEntity.setExpensetype(expense.getExpenseType());
	    expenseEntity.setExpensecomment(expense.getExpenseComment());
	    return expenseEntity;
    }
	
}
