package com.example.springpetproject.group.builder;

import com.example.springpetproject.expense.ExpenseEntity;
import com.example.springpetproject.group.GroupEntity;
import com.example.springpetproject.group.dto.BalanceGroup;
import com.example.springpetproject.user.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BalanceGroupBuilderTest {

    private static final Long GROUP_ID = 1L;
    private static final String GROUP_NAME = "GROUP_NAME";
    private static final String USERNAME_1 = "USERNAME_1";
    private static final String USERNAME_2 = "USERNAME_2";

    private BalanceGroupBuilder balanceGroupBuilder;

    @BeforeEach
    public void init() {
        balanceGroupBuilder = new BalanceGroupBuilder();
    }

    @Test
    public void should_return_balanceGroup_when_there_are_not_expenses() {
        final GroupEntity groupEntity = buildGroupEntity();
        when(groupEntity.getExpenses()).thenReturn(null);

        final BalanceGroup result = balanceGroupBuilder.build(groupEntity);

        Assertions.assertEquals(result.getId(), GROUP_ID);
        Assertions.assertEquals(result.getName(), GROUP_NAME);
        Assertions.assertNull(result.getExpenses());
        Assertions.assertTrue(result.getBalance().isEmpty());
        Assertions.assertTrue(result.getDebts().isEmpty());
    }

    @Test
    public void should_return_balanceGroup() {
        final GroupEntity groupEntity = buildGroupEntity();

        final BalanceGroup result = balanceGroupBuilder.build(groupEntity);

        Assertions.assertEquals(result.getId(), GROUP_ID);
        Assertions.assertEquals(result.getName(), GROUP_NAME);
        Assertions.assertEquals(result.getExpenses().size(), 2);

        Assertions.assertEquals(result.getBalance().get(USERNAME_1), 2.5);
        Assertions.assertEquals(result.getBalance().get(USERNAME_2), -2.5);

        Assertions.assertEquals(result.getDebts().size(), 1);
        String debtMessageExpect = String.format("%s -> %s: (%.2f)", USERNAME_2, USERNAME_1, 2.5);
        Assertions.assertEquals(result.getDebts().get(0), debtMessageExpect);
    }

    private GroupEntity buildGroupEntity() {
        final List<ExpenseEntity> expenses = buildExpenses();

        final GroupEntity groupEntity = mock(GroupEntity.class);
        when(groupEntity.getId()).thenReturn(GROUP_ID);
        when(groupEntity.getName()).thenReturn(GROUP_NAME);
        when(groupEntity.getExpenses()).thenReturn(expenses);

        return groupEntity;
    }

    private List<ExpenseEntity> buildExpenses(){
        final UserEntity user1 = mock(UserEntity.class);
        when(user1.getUsername()).thenReturn(USERNAME_1);

        final ExpenseEntity expense1 = mock(ExpenseEntity.class);
        when(expense1.getAmount()).thenReturn(10D);
        when(expense1.getUser()).thenReturn(user1);

        final UserEntity user2 = mock(UserEntity.class);
        when(user2.getUsername()).thenReturn(USERNAME_2);

        final ExpenseEntity expense2 = mock(ExpenseEntity.class);
        when(expense2.getAmount()).thenReturn(5D);
        when(expense2.getUser()).thenReturn(user2);

        return Arrays.asList(expense1, expense2);
    }

}