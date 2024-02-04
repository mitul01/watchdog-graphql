package com.watchdog.watchdog.dto;

import java.util.Objects;

public record BotInputDTO(String name) {
    public BotInputDTO {
        Objects.requireNonNull(name);
    }
}
