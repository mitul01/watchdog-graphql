package com.watchdog.watchdog.dto;

import java.util.Objects;
import java.util.UUID;

public record UserInputDTO(String userName, String firstName, String lastName, UUID botId) {
    public UserInputDTO {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(botId);
    }
}
