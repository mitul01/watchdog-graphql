package com.watchdog.watchdog.dto;

import java.util.UUID;

public record ContextInputDTO(String chatId, UUID botId, UUID userId, UUID accountId) {
}
