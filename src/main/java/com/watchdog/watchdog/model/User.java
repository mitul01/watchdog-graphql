package com.watchdog.watchdog.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid", updatable = false)
	private UUID userId;

	@NotNull
	private String username;

	@NotNull
	private String firstName;

	private String lastName;

	@ManyToMany
	@JoinTable(
			name = "bots_installed_by_user",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "bot_id")
	)
	private Set<Bot> installedBots;

	@ManyToMany
	@JoinTable(
			name = "accounts",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "account_id")
	)
	private Set<Account> accounts;


	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
	private List<Expense> expenses;
}
