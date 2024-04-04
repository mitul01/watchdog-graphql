package com.watchdog.watchdog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WatchdogApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchdogApplication.class, args);
	}

}
