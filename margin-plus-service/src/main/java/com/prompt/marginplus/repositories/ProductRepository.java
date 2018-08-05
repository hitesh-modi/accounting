package com.prompt.marginplus.repositories;

import java.util.List;

import com.prompt.marginplus.entities.Productdetail;
import com.prompt.marginplus.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Productdetail, Long>{
	
	Productdetail findByProductIdAndUser(long productId, User user);

	List<Productdetail> findAllByUser(User user);
	
}
