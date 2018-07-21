package com.prompt.marginplus.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prompt.marginplus.entities.CustomerDetail;

@Repository("customerRepository")
public interface CustomerRepository extends CrudRepository<CustomerDetail, Long>{
	
}
