package com.watchdog.watchdog.dto;

import java.util.UUID;

public record ExpenseSplitInputDTO(Float amount, UUID userId) {
}
