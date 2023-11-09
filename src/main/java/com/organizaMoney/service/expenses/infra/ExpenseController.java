package com.organizaMoney.service.expenses.infra;

import com.organizaMoney.service.expenses.application.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<FilterDTO>>filter(
            @RequestParam(value = "startDate", defaultValue = "")String startDate,
            @RequestParam(value = "endDate",  defaultValue = "") String endDate,
            @RequestParam(value = "expenseType", required = false) Long expenseTypeId){
        return ResponseEntity.ok().body(expenseService.filter(startDate, endDate, expenseTypeId));
    }

    @GetMapping("/summary")
    public ResponseEntity<List<SummaryDTO>> summary(
            @RequestParam(value = "startDate", defaultValue = "")String startDate,
            @RequestParam(value = "endDate",  defaultValue = "") String endDate,
            @RequestParam(value = "expenseType", required = false) Long expenseTypeId
    ){
        return ResponseEntity.ok().body(expenseService.summary(startDate, endDate, expenseTypeId));
    }

    @GetMapping("/expense-type")
    public ResponseEntity<List<SpendTypeDTO>> spendType(
            @RequestParam(value = "startDate", defaultValue = "")String startDate,
            @RequestParam(value = "endDate",  defaultValue = "") String endDate
    ){
        return ResponseEntity.ok().body(expenseService.spendType(startDate, endDate));
    }
}
