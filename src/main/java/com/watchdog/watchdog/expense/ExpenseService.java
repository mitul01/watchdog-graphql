package com.watchdog.watchdog.expense;

import com.watchdog.watchdog.account.AccountRepository;
import com.watchdog.watchdog.dto.ExpenseInputDTO;
import com.watchdog.watchdog.model.Account;
import com.watchdog.watchdog.model.Expense;
import com.watchdog.watchdog.model.User;
import com.watchdog.watchdog.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Expense> getExpenses(){
        return expenseRepository.findAll();
    }

    public Expense createExpense(ExpenseInputDTO expenseInput){
        Account account = accountRepository.findById(expenseInput.accountId()).orElseThrow(() -> new EntityNotFoundException("No such account exists"));
        User paidByUser = null;
        for(User user: account.getUsers()){
            if(user.getUserId().equals(expenseInput.paidByUserId())){
                System.out.println(user.getUserId());
                 paidByUser = user;
                 break;
            }
        }
        if(paidByUser == null){
            // TODO - Throw exception - user does exist in the selected account
        }
//        User paidByUser = userRepository.findById(expenseInput.paidByUserId()).orElseThrow(() -> new EntityNotFoundException("No such user exists"));
        Expense expense = new Expense(expenseInput.name(),expenseInput.amount(),account,paidByUser);
        expenseRepository.save(expense);

        // TODO - Update expense owed table

        return expense;
    }

    public Expense updateExpense(UUID expenseId, ExpenseInputDTO expenseInput){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new EntityNotFoundException("No such expense exists"));
        if (expenseInput.name()  != null) expense.setName(expenseInput.name());
        if (expenseInput.amount()  != null) expense.setAmount(expenseInput.amount());
        if (expenseInput.paidByUserId()  != null) {
            User paidByUser = null;
            for(User user: expense.getAccount().getUsers()){
                if(user.getUserId().equals(expenseInput.paidByUserId())){
                    System.out.println(user.getUserId());
                    paidByUser = user;
                    break;
                }
            }
            if(paidByUser != null){
                expense.setPaidBy(paidByUser);
            } else {
                // TODO - Throw exception - user does exist in the selected account
            }
        }
        expenseRepository.save(expense);
        return expense;
    }

    public Boolean deleteExpense(UUID expenseId){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new EntityNotFoundException("No such expense exists"));
        expenseRepository.delete(expense);
        return true;
    }
}
