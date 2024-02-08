package com.watchdog.watchdog.dto;

import java.util.UUID;

public record ExpenseInputDTO(String name, Float amount, UUID accountId, UUID paidByUserId) {
}
