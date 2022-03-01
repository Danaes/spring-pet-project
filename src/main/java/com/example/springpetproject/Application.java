package com.example.springpetproject;

import com.example.springpetproject.expense.ExpenseEntity;
import com.example.springpetproject.expense.ExpenseRepository;
import com.example.springpetproject.group.GroupEntity;
import com.example.springpetproject.group.GroupRepository;
import com.example.springpetproject.user.UserEntity;
import com.example.springpetproject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public Application(UserRepository userRepository, GroupRepository groupRepository,
                       ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        populateDatabase();
    }

    public void populateDatabase() {
        List<UserEntity> users = createUsers();
        userRepository.saveAll(users);

        List<GroupEntity> groups = createGroups(users);
        groupRepository.saveAll(groups);

        List<ExpenseEntity> expenses = createExpenses(groups, users);
        expenseRepository.saveAll(expenses);
    }

    private List<GroupEntity> createGroups(List<UserEntity> users){
        GroupEntity group1 = GroupEntity.builder().name("group1").build();
        GroupEntity group2 = GroupEntity.builder().name("group2").build();
        GroupEntity group3 = GroupEntity.builder().name("group3").build();

        return Arrays.asList(group1, group2, group3);
    }

    private List<UserEntity> createUsers() {
        UserEntity user1 = UserEntity.builder().username("user1").build();
        UserEntity user2 = UserEntity.builder().username("user2").build();
        UserEntity user3 = UserEntity.builder().username("user3").build();
        UserEntity user4 = UserEntity.builder().username("user4").build();
        UserEntity user5 = UserEntity.builder().username("user5").build();

        return Arrays.asList(user1, user2, user3, user4, user5);
    }

    private List<ExpenseEntity> createExpenses(List<GroupEntity> groups, List<UserEntity> users) {
        ExpenseEntity expense1 = ExpenseEntity.builder().description("expense1").amount(12.28).user(users.get(0))
                .expenseGroup(groups.get(0)).build();
        ExpenseEntity expense2 = ExpenseEntity.builder().description("expense2").amount(5.0).user(users.get(1))
                .expenseGroup(groups.get(1)).build();
        ExpenseEntity expense3 = ExpenseEntity.builder().description("expense3").amount(15.0).user(users.get(2))
                .expenseGroup(groups.get(2)).build();
        ExpenseEntity expense4 = ExpenseEntity.builder().description("expense4").amount(22.0).user(users.get(3))
                .expenseGroup(groups.get(0)).build();
        ExpenseEntity expense5 = ExpenseEntity.builder().description("expense5").amount(4.6).user(users.get(4))
                .expenseGroup(groups.get(0)).build();

        return Arrays.asList(expense1, expense2, expense3, expense4, expense5);
    }
}
