package com.prompt.marginplus.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prompt.marginplus.entities.SectionMaster;

@Repository("hsnSectionRepo")
public interface HSNSectionRepository extends CrudRepository<SectionMaster, String>{
	
}
