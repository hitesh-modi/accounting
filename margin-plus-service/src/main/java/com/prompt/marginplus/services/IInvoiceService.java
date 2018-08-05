package com.prompt.marginplus.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.DocumentException;
import com.prompt.marginplus.models.Invoice;

public interface IInvoiceService {

	public String createInvoice(final Invoice invoice, String user);

	public File createInvoicePDF(String invoiceId, String userId) throws DocumentException, MalformedURLException, IOException;

	public Invoice getInvoice(String invoiceId, String userId);

    public void receivePayment(String invoiceId, String amount);
	
}
