package com.prompt.marginplus.repositories;

import com.prompt.marginplus.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prompt.marginplus.entities.CustomerDetail;

import java.util.Collection;

@Repository("customerRepository")
public interface CustomerRepository extends CrudRepository<CustomerDetail, Long>{
	public Collection<CustomerDetail> findAllByUser(User user);
}
