package com.watchdog.watchdog.dto;

import com.watchdog.watchdog.model.User;

public record ExpenseOwedDTO(User user, Float owedAmount) {
}
