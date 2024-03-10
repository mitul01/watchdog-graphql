package com.watchdog.watchdog.account;

import com.watchdog.watchdog.dto.AccountInputDTO;
import com.watchdog.watchdog.model.Account;

import java.util.UUID;

public interface UseCases {
    Iterable<Account> accounts();
    Account createAccount(AccountInputDTO accountInput);
    Account updateAccount(UUID accountId, AccountInputDTO accountInput);
    Boolean deleteAccount(UUID accountId);
}
