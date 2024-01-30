package com.watchdog.watchdog.model;

import javax.persistence.*;
import java.util.UUID;

public class Expense {
	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid", updatable = false)
	private UUID id;

	private String name;

	private Float amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account.accountId")
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user.userId")
	private User user;
}
