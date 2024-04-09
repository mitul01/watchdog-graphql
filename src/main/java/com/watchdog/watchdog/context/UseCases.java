package com.watchdog.watchdog.context;

import com.watchdog.watchdog.dto.ContextInputDTO;
import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.Context;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.User;
import com.watchdog.watchdog.model.enums.SortDirection;

import java.util.List;
import java.util.UUID;

public interface UseCases {
    Iterable<Context> contexts(List<FieldFilter> filter, String sortField, SortDirection sortDirection, int limit);
    Context createContext(ContextInputDTO contextInput);
    Context updateContext(String chatId, ContextInputDTO contextInput);
    Boolean deleteContext(String chatId);

}
