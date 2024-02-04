package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.model.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BotRepository extends JpaRepository<Bot, UUID> {
}
