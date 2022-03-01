package com.example.springpetproject.expense.impl;

import com.example.springpetproject.expense.ExpenseEntity;
import com.example.springpetproject.expense.ExpenseRepository;
import com.example.springpetproject.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<ExpenseEntity> getExpenses() {
        return expenseRepository.findAll();
    }
}
