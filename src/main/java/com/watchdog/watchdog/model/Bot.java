package com.watchdog.watchdog.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "bot")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bot {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid", updatable = false)
	private UUID botId;

	@NotNull
	@Column(unique = true)
	public String name;

	@ManyToMany(mappedBy = "installedBots")
	public Set<User> users;

	public Bot(String name) {
		this.name = name;
	}
}
