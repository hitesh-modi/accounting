package com.prompt.marginplus.repositories;

import com.prompt.marginplus.entities.CreditNote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("creditNoteRepo")
public interface CreditNoteRepository extends CrudRepository<CreditNote, String>{

}
