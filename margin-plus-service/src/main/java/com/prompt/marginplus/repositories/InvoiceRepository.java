package com.prompt.marginplus.repositories;

import java.util.Collection;
import java.util.Date;

import com.prompt.marginplus.entities.Invoicedetail;
import com.prompt.marginplus.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("invoiceRepo")
public interface InvoiceRepository extends CrudRepository<Invoicedetail, String>{
	@Query("select invoice from Invoicedetail invoice where user.userid=:userid and ID_InvoiceDate between :fromDate and :toDate")
	public Collection<Invoicedetail> getInvoiceWithDateRange(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, @Param("userid") String userid);

	public Collection<Invoicedetail> findAllByUser(User user);

	public Collection<Invoicedetail> findTop30ByUser(User user);
}
