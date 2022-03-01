package com.example.springpetproject.group.builder;

import com.example.springpetproject.expense.ExpenseEntity;
import com.example.springpetproject.group.GroupEntity;
import com.example.springpetproject.group.dto.BalanceGroup;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BalanceGroupBuilder{

    private static final double ZERO = 0F;

    public BalanceGroup build(GroupEntity group){
        Map<String, Double> balance = generateBalance(group.getExpenses());
        List<String> debts = new ArrayList<>();
        if(!balance.isEmpty()){
            final HashMap<String, Double> deepCopyOfBalance = new HashMap<>(balance);
            calculateDebts(deepCopyOfBalance, debts);
        }
        return new BalanceGroup(group, balance, debts);
    }

    private Map<String, Double> generateBalance(List<ExpenseEntity> expenses) {
        if(CollectionUtils.isEmpty(expenses)){
            return Collections.emptyMap();
        }

        final HashMap<String, Double> balance = new HashMap<>();

        Double totalExpenses = ZERO;
        for(ExpenseEntity expense : expenses) {
            totalExpenses += expense.getAmount();
            addExpenseToBalance(balance, expense);
        }

        calculateBalance(balance, totalExpenses);

        return balance;
    }

    private void addExpenseToBalance(HashMap<String, Double> balance, ExpenseEntity expense) {
        final String username = expense.getUser().getUsername();
        Double amount = expense.getAmount();

        if(balance.containsKey(username)){
            balance.replace(username, balance.get(username) + amount);
        } else {
            balance.put(username, amount);
        }
    }

    private void calculateBalance(HashMap<String, Double> balance, Double totalExpenses) {
        final Double mean = totalExpenses / balance.size();
        balance.forEach((username, amount) -> {
            Double differenceAmount = round((amount - mean), 2);
            balance.replace(username, differenceAmount);
        });
    }

    private void calculateDebts(Map<String, Double> balance, List<String> debts) {
        Double Max_Value = Collections.max(balance.values());
        Double Min_Value = Collections.min(balance.values());

        if (!Max_Value.equals(Min_Value) && Min_Value.compareTo(-0.1) != 0) {
            String Max_Key = getKeyFromValue(balance, Max_Value);
            String Min_Key = getKeyFromValue(balance, Min_Value);
            Double result = round(Max_Value + Min_Value, 1);
            
            balance.remove(Max_Key);
            balance.remove(Min_Key);

            if (result >= ZERO) {
                debts.add(String.format("%s -> %s: (%.2f)", Min_Key, Max_Key, round(Math.abs(Min_Value), 2)));
                balance.put(Max_Key, result);
                balance.put(Min_Key, ZERO);
            } else {
                debts.add(String.format("%s -> %s: (%.2f)", Min_Key, Max_Key, round(Math.abs(Max_Value), 2)));
                balance.put(Max_Key, ZERO);
                balance.put(Min_Key, result);
            }

            calculateDebts(balance, debts);
        }
    }
    
    private String getKeyFromValue(Map<String, Double> balance, Double amount) {
        for (String username : balance.keySet()) {
            if (balance.get(username).equals(amount)) {
                return username;
            }
        }
        return null;
    }

    private Double round(Double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
