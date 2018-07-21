package com.prompt.marginplus.repositories;

import com.prompt.marginplus.entities.ConsigneeDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("consigneeRepository")
public interface ConsigneeRepository extends CrudRepository<ConsigneeDetail, Long>{
	@Query("select consignee from ConsigneeDetail consignee where consigneeName = :name and consigneeMobile = :mobileNumber")
	public ConsigneeDetail getConsigneeByNameAndMobileNumber(@Param("name") String name, @Param("mobileNumber") String mobileNumber); 
}
