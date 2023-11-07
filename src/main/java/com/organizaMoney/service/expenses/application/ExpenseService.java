package com.organizaMoney.service.expenses.application;

import com.organizaMoney.service.expenses.domain.Expense;
import com.organizaMoney.service.expenses.domain.ExpenseType;
import com.organizaMoney.service.expenses.infra.ExpenseRepository;
import com.organizaMoney.service.user.application.UserServices;
import com.organizaMoney.service.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserServices userServices;

    public ExpenseService(ExpenseRepository expenseRepository,
                          UserServices userServices){
        this.expenseRepository = expenseRepository;
        this.userServices = userServices;
    }

    public ExpenseDTO save(ExpenseDTO expenseDTO){
        User user = userServices.getLoggedUser();
        ExpenseType expenseType = new ExpenseType();
        expenseType.setId(expenseDTO.expenseType.getId());
        Expense expense = new Expense();
        expense.setUser(user);
        expense.setSpend(expenseDTO.getValue());
        expense.setDate(expenseDTO.getDate());
        expense.setExpenseType(expenseType);
        return new ExpenseDTO(expenseRepository.save(expense));
    }
}
