package com.watchdog.watchdog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	@NotNull
	private Float amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account.accountId")
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userbase.userId")
	private User user;
}
