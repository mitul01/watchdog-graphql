package com.watchdog.watchdog.user;

import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.User;

import java.util.UUID;

public interface UseCases {
     Iterable<User> users();
     User createUser(UserInputDTO userInput);
     User updateUser(UUID userId,UserInputDTO userInput);
     Boolean deleteUser(UUID userId);

}
