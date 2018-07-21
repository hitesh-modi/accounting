package com.prompt.marginplus.repositories;

import java.util.Collection;
import java.util.Date;

import com.prompt.marginplus.entities.Invoicedetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("invoiceRepo")
public interface InvoiceRepository extends CrudRepository<Invoicedetail, String>{
	@Query("select invoice from Invoicedetail invoice where ID_InvoiceDate between :fromDate and :toDate")
	public Collection<Invoicedetail> getInvoiceWithDateRange(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
