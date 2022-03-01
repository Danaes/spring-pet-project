package com.example.springpetproject.group.dto;

import com.example.springpetproject.group.GroupEntity;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class BalanceGroup extends GroupEntity {

    private Map<String, Double> balance;
    private List<String> debts;

    public BalanceGroup(GroupEntity group, Map<String, Double> balance, List<String> debts) {
        super(group.getId(), group.getName(), group.getExpenses());
        this.balance = balance;
        this.debts = debts;
    }
}
