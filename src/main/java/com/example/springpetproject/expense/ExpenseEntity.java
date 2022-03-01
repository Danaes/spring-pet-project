package com.example.springpetproject.expense;

import com.example.springpetproject.group.GroupEntity;
import com.example.springpetproject.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
@Builder
@NoArgsConstructor
@Getter
@ToString
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private GroupEntity expenseGroup;

    public ExpenseEntity(Long id, String description, Double amount, UserEntity user, GroupEntity expenseGroup) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.user = user;
        this.expenseGroup = expenseGroup;
    }
}
