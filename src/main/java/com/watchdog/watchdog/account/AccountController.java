package com.watchdog.watchdog.account;

import com.watchdog.watchdog.dto.AccountInputDTO;
import com.watchdog.watchdog.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AccountController implements UseCases{

    @Autowired
    private AccountService accountService;

    @QueryMapping
    public Iterable<Account> accounts() {
        return accountService.getAccounts();
    }

    @MutationMapping
    public Account createAccount(@Argument AccountInputDTO account){
        return accountService.createAccount(account);
    }

    @MutationMapping
    public Account updateAccount(@Argument UUID accountId, @Argument AccountInputDTO account){
        return accountService.updateAccount(accountId,account);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument UUID accountId){
        return accountService.deleteAccount(accountId);
    }
}
