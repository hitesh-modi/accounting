package com.prompt.marginplus.repositories;

import java.util.Collection;

import com.prompt.marginplus.entities.HsnChapterMap;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("hsnRepo")
public interface HSNRepository extends CrudRepository<HsnChapterMap, String>{
	@Query("select hsn from HsnChapterMap hsn where hsnchapterMaster.hsnchapterId = :chapterId ")
	public Collection<HsnChapterMap> getHSNByChapter(@Param("chapterId") String chapterId);
}
