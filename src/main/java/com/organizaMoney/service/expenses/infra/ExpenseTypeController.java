package com.organizaMoney.service.expenses.infra;

import com.organizaMoney.service.expenses.application.ExpenseTypeDTO;
import com.organizaMoney.service.expenses.application.ExpenseTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/expenseType")
public class ExpenseTypeController {
    private final ExpenseTypeService expenseTypeService;

    public ExpenseTypeController(ExpenseTypeService expenseTypeService){
        this.expenseTypeService = expenseTypeService;
    }

    @PostMapping("")
    public ResponseEntity<ExpenseTypeDTO> save(@Valid @RequestBody ExpenseTypeDTO expenseTypeDTO){
        return ResponseEntity.ok().body(this.expenseTypeService.save(expenseTypeDTO));
    }
}
