package com.watchdog.watchdog.dto;

import java.util.List;
import java.util.UUID;

public record AccountInputDTO(String name, List<UUID> userIds) {
}
