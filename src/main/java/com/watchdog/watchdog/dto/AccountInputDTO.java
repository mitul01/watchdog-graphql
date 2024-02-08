package com.watchdog.watchdog.dto;

import com.watchdog.watchdog.model.Expense;

import java.util.List;
import java.util.UUID;

public record AccountInputDTO(String name, List<UUID> userIds) {
}
