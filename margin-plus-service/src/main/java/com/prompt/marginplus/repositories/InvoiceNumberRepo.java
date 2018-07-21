package com.prompt.marginplus.repositories;

import java.sql.Date;

import com.prompt.marginplus.entities.InvoiceNumberDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("invoiceNumberRepo")
public interface InvoiceNumberRepo extends CrudRepository<InvoiceNumberDetail, Long>{
	@Query("select max(sequenceNo) from InvoiceNumberDetail where invoiceDate = :date")
	public Integer getInvoiceSequenceNumber(Date date);
}
