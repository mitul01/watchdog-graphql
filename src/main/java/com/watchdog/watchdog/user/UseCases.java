package com.watchdog.watchdog.user;

import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.User;

public interface UseCases {
     Iterable<User> users();
     User createUser(UserInputDTO userInput);

}
