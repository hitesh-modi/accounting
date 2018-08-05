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
import com.prompt.marginplus.entities.User;
import com.prompt.marginplus.models.InvoiceReportModel;
import com.prompt.marginplus.repositories.UserRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.prompt.marginplus.repositories.InvoiceRepository;

@Component("invoiceReportService")
public class InvoiceReportService implements IInvoiceReportService{

	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceReportService.class);
	
	@Resource
	private InvoiceRepository invoiceRepo;

	@Resource
	private UserRepository userRepository;
	
	private static final BigDecimal ZERO = new BigDecimal(0);

	@Override
	public Collection<InvoiceReportModel> getInvoices(final Date fromDate, final Date toDate, final String userid) {

		User user = userRepository.getUser(userid);

		Collection<Invoicedetail> invoicesFromDB = null;
		if(fromDate == null && toDate == null) {
			// TODO fetch first 30 records if date range is not provided.
			invoicesFromDB = invoiceRepo.findTop30ByUser(user);
		} else {
			invoicesFromDB = invoiceRepo.getInvoiceWithDateRange(fromDate, toDate, userid);
		}

		LOGGER.info("Invoices fetched from Database: " + invoicesFromDB);

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
