package com.watchdog.watchdog.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "userbase")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid", updatable = false)
	private UUID userId;

	@NotNull
	private String userName;

	private String firstName;

	private String lastName;

	public User(String userName, String firstName, String lastName, Set<Bot> installedBots) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.installedBots = installedBots;
	}

	@ManyToMany
	@JoinTable(
			name = "bots_installed_by_user",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "bot_id")
	)
	private Set<Bot> installedBots;

	@ManyToMany(mappedBy = "users")
	private Set<Account> accounts;


	@OneToMany(mappedBy = "paidBy", fetch = FetchType.LAZY, orphanRemoval = false)
	private List<Expense> totalExpensesPaid;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
	private List<ExpenseSplit> ownExpenses;
}
