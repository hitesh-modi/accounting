package com.prompt.marginplus.repositories;

import java.util.List;

import com.prompt.marginplus.entities.Productdetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Productdetail, Long>{
	
	List<Productdetail> findByProductId(int productId);
	
}
