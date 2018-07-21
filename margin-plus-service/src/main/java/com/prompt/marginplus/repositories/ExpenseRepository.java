package com.prompt.marginplus.repositories;

import com.prompt.marginplus.entities.ExpensesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("expenseRepository")
public interface ExpenseRepository extends CrudRepository<ExpensesEntity, Long>{
	

}
