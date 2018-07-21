package com.prompt.marginplus.services;

import com.prompt.marginplus.models.Expense;
import com.prompt.marginplus.types.ExpenseType;

/**
 * Created by Hitesh Modi on 02-03-2018.
 */
public interface IExpenseService {
    public Expense getExpense(long expenseId);
    public Long saveExpense(Expense expense);
    public ExpenseType[] getExpenseTypes();
}
