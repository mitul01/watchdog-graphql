package com.watchdog.watchdog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "expense")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid", updatable = false)
	private UUID id;

	@NotNull
	private String name;

	public Expense(String name, Float amount, Account account, User paidBy) {
		this.name = name;
		this.amount = amount;
		this.account = account;
		this.paidBy = paidBy;
	}

	@NotNull
	private Float amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account.accountId")
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userbase.userId")
	private User paidBy;

	@OneToMany(mappedBy = "expense", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ExpenseSplit> expenseSplit;
}
