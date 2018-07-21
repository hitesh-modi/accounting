package com.prompt.marginplus.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.prompt.marginplus.entities.Invoicedetail;
import com.prompt.marginplus.models.InvoiceReportModel;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import com.prompt.marginplus.repositories.InvoiceRepository;

@Component("invoiceReportService")
public class InvoiceReportService implements IInvoiceReportService{
	
	@Resource
	private InvoiceRepository invoiceRepo;
	
	private static final BigDecimal ZERO = new BigDecimal(0);

	@Override
	public Collection<InvoiceReportModel> getInvoices(final Date fromDate, final Date toDate) {
		
		Collection<Invoicedetail> invoicesFromDB = invoiceRepo.getInvoiceWithDateRange(fromDate, toDate);
		List<InvoiceReportModel> invoiceModels = new ArrayList<>();
		for (Invoicedetail invoicedetail : invoicesFromDB) {
			
			Date invoiceDueDate = invoicedetail.getID_InvoiceDueDate();
			boolean dateDanger = false;
			boolean amountDanger = false;
			
			InvoiceReportModel invoiceModel = new InvoiceReportModel();
			invoiceModel.setInvoiceId(invoicedetail.getID_InvoiceId());
			invoiceModel.setInvoiceNumber(invoicedetail.getID_InvoiceNumber());
			invoiceModel.setInvoiceDate(invoicedetail.getID_InvoiceDate());
			invoiceModel.setInvoiceDueDate(invoiceDueDate);
			invoiceModel.setConsigneeName(invoicedetail.getConsigneeDetail().getConsigneeName());
			invoiceModel.setCustomerName(invoicedetail.getCustomerDetail().getCdCustomerName());
			invoiceModel.setInvoiceAmount(invoicedetail.getID_GrandTotal());
			invoiceModel.setBalanceAmount(invoicedetail.getID_InvoiceBalanceAmount());
			
			Date dateWeekAfterDueDate = DateUtils.addDays(invoiceDueDate, 7);
			if(dateWeekAfterDueDate.after(Calendar.getInstance().getTime())) {
				dateDanger = true;
			}
			
			if(!invoicedetail.getID_InvoiceBalanceAmount().equals(ZERO)) {
				amountDanger = true;
			}
			invoiceModel.setDanger(dateDanger && amountDanger);
			invoiceModels.add(invoiceModel);
			
			Collections.sort(invoiceModels, new Comparator<InvoiceReportModel>() {
				@Override
				public int compare(InvoiceReportModel o1, InvoiceReportModel o2) {
					Date invoiceDate1 = o1.getInvoiceDate();
					Date invoiceDate2 = o2.getInvoiceDate();
					if(invoiceDate1.after(invoiceDate2)) {
						return -1;
					}
					else if(invoiceDate1.before(invoiceDate2)) {
						return 1;
					}
					else
						return 0;
				}
			});
		}
		return invoiceModels;
	}
	
}
