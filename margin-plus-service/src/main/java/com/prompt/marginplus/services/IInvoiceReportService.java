package com.prompt.marginplus.services;

import java.util.Collection;
import java.util.Date;

import com.prompt.marginplus.models.InvoiceReportModel;

public interface IInvoiceReportService {

	Collection<InvoiceReportModel> getInvoices(Date fromDate, Date toDate);

}
