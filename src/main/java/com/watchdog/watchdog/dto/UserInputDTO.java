package com.watchdog.watchdog.dto;

import java.util.UUID;

public record UserInputDTO(String userName, String firstName, String lastName, UUID botId) {
}
