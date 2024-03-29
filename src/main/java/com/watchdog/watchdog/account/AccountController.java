package com.watchdog.watchdog.account;

import com.watchdog.watchdog.dto.AccountInputDTO;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.model.Account;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.enums.SortDirection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class AccountController implements UseCases{

    @Autowired
    private AccountService accountService;

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @QueryMapping
    public Iterable<Account> accounts(
            @Argument List<FieldFilter> filter,
            @Argument String sortField,
            @Argument SortDirection sortDirection,
            @Argument int limit) {
        logger.info(Constants.getRequestInfoLogMsg.formatted("account"));
        return accountService.getAccountsByCriteria(filter, sortField, sortDirection, limit);
    }

    @MutationMapping
    public Account createAccount(@Argument AccountInputDTO account){
        logger.info(Constants.createRequestInfoLogMsg.formatted("account",account.toString()));
        return accountService.createAccount(account);
    }

    @MutationMapping
    public Account updateAccount(@Argument UUID accountId, @Argument AccountInputDTO account){
        logger.info(Constants.updateRequestInfoLogMsg.formatted("account",accountId.toString(),account.toString()));
        return accountService.updateAccount(accountId,account);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument UUID accountId){
        logger.info(Constants.deleteRequestInfoLogMsg.formatted("account",accountId.toString()));
        return accountService.deleteAccount(accountId);
    }
}
