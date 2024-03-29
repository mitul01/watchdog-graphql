package com.watchdog.watchdog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
