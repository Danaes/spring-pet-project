package com.example.springpetproject.user;

import com.example.springpetproject.expense.ExpenseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "user")
@Builder
@NoArgsConstructor
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ExpenseEntity> expenses;

    public UserEntity(Long id, String username, List<ExpenseEntity> expenses) {
        this.id = id;
        this.username = username;
        this.expenses = expenses;
    }
}
