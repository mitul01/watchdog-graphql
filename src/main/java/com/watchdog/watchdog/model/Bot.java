package com.watchdog.watchdog.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "bot")
public class Bot {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid", updatable = false)
	private UUID botId;

	@NotNull
	public String type;

	@ManyToMany(mappedBy = "installedBots")
	public Set<User> users;

}
