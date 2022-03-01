package com.example.springpetproject.expense;

import com.example.springpetproject.expense.impl.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseServiceImpl expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/")
    ResponseEntity<List<ExpenseEntity>> getAllExpenses(){
        List<ExpenseEntity> expenses = expenseService.getExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
}
