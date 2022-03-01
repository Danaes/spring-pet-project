package com.example.springpetproject.group;

import com.example.springpetproject.expense.ExpenseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "expense_group")
@Builder
@NoArgsConstructor
@Getter
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @OneToMany(mappedBy = "expenseGroup")
    @JsonManagedReference
    private List<ExpenseEntity> expenses;

    public GroupEntity(Long id, String name, List<ExpenseEntity> expenses) {
        this.id = id;
        this.name = name;
        this.expenses = expenses;
    }
}
