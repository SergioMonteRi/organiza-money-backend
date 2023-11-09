package com.organizaMoney.service.expenses.application;

import com.organizaMoney.service.expenses.domain.Expense;
import com.organizaMoney.service.expenses.domain.ExpenseType;
import com.organizaMoney.service.expenses.infra.ExpenseRepository;
import com.organizaMoney.service.user.application.UserServices;
import com.organizaMoney.service.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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

    public Page<TableDataDTO> index(String startDate, String endDate, Long expenseTypeId, Pageable pageable){
        LocalDate min = "".equals(startDate) ? null : LocalDate.parse(startDate);
        LocalDate max = "".equals(endDate) ? null : LocalDate.parse(endDate);
        return expenseRepository.index(min, max, expenseTypeId, pageable);
    }

    @Transactional(readOnly = true)
    public List<FilterDTO> filter(String startDate, String endDate, Long expenseTypeId){
        LocalDate min = "".equals(startDate) ? null : LocalDate.parse(startDate);
        LocalDate max = "".equals(endDate) ? null : LocalDate.parse(endDate);
        return expenseRepository.filter(min, max, expenseTypeId);
    }

    @Transactional(readOnly = true)
    public List<SummaryDTO> summary(String startDate, String endDate, Long expenseTypeId){
        LocalDate min = "".equals(startDate) ? null : LocalDate.parse(startDate);
        LocalDate max = "".equals(endDate) ? null : LocalDate.parse(endDate);
        return expenseRepository.summary(min, max, expenseTypeId);
    }

    @Transactional(readOnly = true)
    public List<SpendTypeDTO> spendType(String startDate, String endDate){
        LocalDate min = "".equals(startDate) ? null : LocalDate.parse(startDate);
        LocalDate max = "".equals(endDate) ? null : LocalDate.parse(endDate);
        return expenseRepository.spendType(min, max);
    }
}
