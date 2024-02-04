package com.watchdog.watchdog.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "account")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
