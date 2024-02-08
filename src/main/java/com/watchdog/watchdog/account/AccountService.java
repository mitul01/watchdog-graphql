package com.watchdog.watchdog.account;

import com.watchdog.watchdog.dto.AccountInputDTO;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.model.Account;
import com.watchdog.watchdog.model.Expense;
import com.watchdog.watchdog.model.User;
import com.watchdog.watchdog.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public void createDefaultAccount(User user){
        String defaultAccountName = Constants.defaultAccountName;
        Set<User> users = new HashSet<User>();
        users.add(userRepository.findById(user.getUserId()).orElseThrow(() -> new EntityNotFoundException("No such user exists")));
        Account account = new Account(defaultAccountName,users);
        accountRepository.save(account);
    }

    public Account createAccount(AccountInputDTO accountInputDTO){
        Set<User> users = new HashSet<User>();
        for(UUID userId: accountInputDTO.userIds()) {
            users.add(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("No such user exists")));
        }
        Account account = new Account(accountInputDTO.name(),users);
        accountRepository.save(account);
        return account;
    }

    public Account updateAccount(UUID accountId, AccountInputDTO accountInputDTO){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("No such account exists"));
        Set<User> users = account.getUsers();
        if(accountInputDTO.userIds() != null) {
            for (UUID userId : accountInputDTO.userIds()) {
                users.add(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("No such user exists")));
            }
        }

        account.setName(accountInputDTO.name());
        account.setUsers(users);
        accountRepository.save(account);
        return account;
    }

    public Boolean deleteAccount(UUID accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("No such account exists"));
        accountRepository.delete(account);
        return true;
    }
}
