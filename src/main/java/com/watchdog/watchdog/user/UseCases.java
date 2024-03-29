package com.watchdog.watchdog.user;

import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.User;
import com.watchdog.watchdog.model.enums.SortDirection;

import java.util.List;
import java.util.UUID;

public interface UseCases {
     Iterable<User> users(List<FieldFilter> filter, String sortField, SortDirection sortDirection, int limit);
     User createUser(UserInputDTO userInput);
     User updateUser(UUID userId,UserInputDTO userInput);
     Boolean deleteUser(UUID userId);

}
