package com.prompt.marginplus.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prompt.marginplus.entities.SacHeadingMaster;

@Repository("sacHeadingRepo")
public interface SacHeadingRepository extends CrudRepository<SacHeadingMaster, String>{
	
}
