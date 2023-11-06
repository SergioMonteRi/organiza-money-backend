package com.organizaMoney.service.expenses.application;

import com.organizaMoney.service.expenses.domain.ExpenseType;
import com.organizaMoney.service.expenses.infra.ExpenseTypeRepository;
import com.organizaMoney.service.user.application.UserServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseTypeService {
    private final ExpenseTypeRepository expenseTypeRepository;
    private final UserServices userServices;
    public ExpenseTypeService(ExpenseTypeRepository expenseTypeRepository,
                              UserServices userServices) {
        this.expenseTypeRepository = expenseTypeRepository;
        this.userServices = userServices;
    }
    @Transactional
    public ExpenseTypeDTO save(ExpenseTypeDTO expenseTypeDTO){
        ExpenseType expenseType = new ExpenseType();
        expenseType.setUser(userServices.getLoggedUser());
        expenseType.setName(expenseTypeDTO.getName());
        return new ExpenseTypeDTO(expenseTypeRepository.save(expenseType));
    }
}
