package com.organizaMoney.service.expenses.infra;

import com.organizaMoney.service.expenses.application.ExpenseDTO;
import com.organizaMoney.service.expenses.application.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> save(@Valid @RequestBody ExpenseDTO expenseDTO){
        return ResponseEntity.ok().body(expenseService.save(expenseDTO));
    }

}
