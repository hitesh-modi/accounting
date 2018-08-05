package com.prompt.marginplus.repositories;

import com.prompt.marginplus.entities.ConsigneeDetail;
import com.prompt.marginplus.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("consigneeRepository")
public interface ConsigneeRepository extends CrudRepository<ConsigneeDetail, Long>{

	public Collection<ConsigneeDetail> findAllByUser(User user);

	@Query("select consignee from ConsigneeDetail consignee where consigneeName = :name and consigneeMobile = :mobileNumber")
	public ConsigneeDetail getConsigneeByNameAndMobileNumber(@Param("name") String name, @Param("mobileNumber") String mobileNumber); 
}
