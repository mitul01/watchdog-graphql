package com.watchdog.watchdog.account;

import com.watchdog.watchdog.dto.AccountInputDTO;
import com.watchdog.watchdog.model.Account;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.enums.SortDirection;

import java.util.List;
import java.util.UUID;

public interface UseCases {
    Iterable<Account> accounts(List<FieldFilter> filter, String sortField, SortDirection sortDirection, int limit);
    Account createAccount(AccountInputDTO accountInput);
    Account updateAccount(UUID accountId, AccountInputDTO accountInput);
    Boolean deleteAccount(UUID accountId);
}
