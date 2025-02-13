package com.watchdog.watchdog.account;

import com.watchdog.watchdog.common.Pagination;
import com.watchdog.watchdog.common.FilterSpecifications;
import com.watchdog.watchdog.common.FilterValidator;
import com.watchdog.watchdog.dto.AccountInputDTO;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.model.Account;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.User;
import com.watchdog.watchdog.model.enums.SortDirection;
import com.watchdog.watchdog.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FilterValidator<Account> filterValidator;
    @Autowired
    private Pagination pagination;

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    public Iterable<Account> getAccountsByCriteria(List<FieldFilter> filterMap, String sortField, SortDirection sortDirection, int limit) {
        filterValidator.isFilterValid(filterMap, Account.class);
        return accountRepository.findAll(FilterSpecifications.buildSpecification(filterMap, Account.class),
                pagination.createPageable(sortField, sortDirection, limit)
        );
    }

    public void createDefaultAccount(User user){
        String defaultAccountName = Constants.defaultAccountName;
        Set<User> users = new HashSet<User>();
        users.add(userRepository.findById(user.getUserId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("user"))));
        Account account = new Account(defaultAccountName,users);
        accountRepository.save(account);
        logger.info(Constants.createDefaultAccountInfoLogMsg.formatted(user.getUserId().toString()));
    }

    public Account createAccount(AccountInputDTO accountInputDTO){
        Set<User> users = new HashSet<User>();
        for(UUID userId: accountInputDTO.userIds()) {
            users.add(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("user"))));
        }
        Account account = new Account(accountInputDTO.name(),users);
        accountRepository.save(account);
        return account;
    }

    public Account updateAccount(UUID accountId, AccountInputDTO accountInputDTO){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("account")));
        Set<User> users = account.getUsers();
        if(accountInputDTO.userIds() != null) {
            for (UUID userId : accountInputDTO.userIds()) {
                users.add(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("user"))));
            }
        }

        account.setName(accountInputDTO.name());
        account.setUsers(users);
        accountRepository.save(account);
        return account;
    }

    public Boolean deleteAccount(UUID accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("account")));
        accountRepository.delete(account);
        return true;
    }
}
