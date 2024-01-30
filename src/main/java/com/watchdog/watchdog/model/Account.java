package com.watchdog.watchdog.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid", updatable = false)
	private UUID accountId;

	@NotNull
	private String name;

	@ManyToMany(mappedBy = "accounts")
	private Set<User> users;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, orphanRemoval = false)
	private List<Expense> expenses;

}
