package com.watchdog.watchdog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "expense_split")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseSplit {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense.id")
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userbase.userId")
    private User user;

    private Float amount;

    public ExpenseSplit(Expense expense, User user, Float amount, String name) {
        this.expense = expense;
        this.user = user;
        this.amount = amount;
        this.name = name;
    }

}
