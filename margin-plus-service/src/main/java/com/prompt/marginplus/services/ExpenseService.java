package com.prompt.marginplus.services;

import com.prompt.marginplus.entities.ExpensesEntity;
import com.prompt.marginplus.models.Expense;
import com.prompt.marginplus.models.UserModel;
import com.prompt.marginplus.repositories.ExpenseRepository;
import com.prompt.marginplus.types.ExpenseType;
import com.prompt.marginplus.utility.ConversionUtility;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by Hitesh Modi on 02-03-2018.
 */
@Component("expenseService")
public class ExpenseService implements IExpenseService {

    @Resource(name = "expenseRepository")
    private ExpenseRepository expenseRepo;

    @Resource(name = "userService")
    private IUserService userService;

    @Override
    public Expense getExpense(long expenseId) {
        Optional<ExpensesEntity> expensesEntity = expenseRepo.findById(expenseId);
        return ConversionUtility.convertExpenseEntityToModel(expensesEntity.get());
    }

    @Override
    public Long saveExpense(Expense expense) {
        UserModel user = userService.getUserInfo();
        ExpensesEntity entity = ConversionUtility.convertExpenseModelToEntity(expense);
        entity.setUserid(user.getUserid());
        ExpensesEntity savedEntity = expenseRepo.save(entity);
        return savedEntity.getExpenseid();
    }

    @Override
    public ExpenseType[] getExpenseTypes() {
        return ExpenseType.values();
    }
}
