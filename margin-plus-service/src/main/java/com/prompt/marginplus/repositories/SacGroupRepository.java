package com.prompt.marginplus.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prompt.marginplus.entities.SacGroupHeadinMap;

@Repository("sacGroupRepo")
public interface SacGroupRepository extends CrudRepository<SacGroupHeadinMap, String>{
	
	@Query("select s from  SacGroupHeadinMap s where sacHeadingMaster.headingId = :headingId")
	public Collection<SacGroupHeadinMap> getGroupsByHeading(@Param("headingId") String headingId);
	
}
