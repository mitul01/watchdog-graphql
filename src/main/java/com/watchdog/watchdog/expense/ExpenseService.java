package com.watchdog.watchdog.expense;

import com.watchdog.watchdog.account.AccountRepository;
import com.watchdog.watchdog.dto.ExpenseInputDTO;
import com.watchdog.watchdog.dto.ExpenseSplitInputDTO;
import com.watchdog.watchdog.model.Account;
import com.watchdog.watchdog.model.Expense;
import com.watchdog.watchdog.model.ExpenseSplit;
import com.watchdog.watchdog.model.User;
import com.watchdog.watchdog.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseSplitRepository expenseSplitRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Expense> getExpenses(){
        return expenseRepository.findAll();
    }

    public Expense createExpense(ExpenseInputDTO expenseInput) {
        Account account = accountRepository.findById(expenseInput.accountId()).orElseThrow(() -> new EntityNotFoundException("No such account exists"));
        User paidByUser = null;
        for(User user: account.getUsers()){
            if(user.getUserId().equals(expenseInput.paidByUserId())){
                 paidByUser = user;
                 break;
            }
        }
        if(paidByUser == null){
            // TODO - Throw exception - user does exist in the selected account
        }
        Expense expense = new Expense(expenseInput.name(),expenseInput.amount(),account,paidByUser);

        // Update expense split table
        List<ExpenseSplit> expenseSplitList = new ArrayList<>();
        Float totalSplitSum = 0.0f;
        if(expense.getAccount().getUsers().size() > 1) {
            if(expenseInput.expenseSplit() != null) {
                for (ExpenseSplitInputDTO expenseSplitInput : expenseInput.expenseSplit()) {
                    User user = userRepository.findById(expenseSplitInput.userId()).orElseThrow(() -> new EntityNotFoundException("No such user exists"));
                    ExpenseSplit expenseSplit = new ExpenseSplit(expense, user, expenseSplitInput.amount(), expense.getName());
                    expenseSplitList.add(expenseSplit);
                    totalSplitSum += expenseSplit.getAmount();
                }
                assert totalSplitSum.equals(expense.getAmount()) : new AssertionError("Split Expenses sum does not match total expense sum");
                expenseRepository.save(expense);
                expenseSplitRepository.saveAll(expenseSplitList);
            } else {
                // TODO - Raise exception of split required
            }
        } else {
            ExpenseSplit expenseSplit = new ExpenseSplit(expense, paidByUser, expense.getAmount(), expense.getName());
            expenseRepository.save(expense);
            expenseSplitRepository.save(expenseSplit);
        }

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

        // Update expense split table
        List<ExpenseSplit> expenseSplitList = new ArrayList<>();
        Float totalSplitSum = 0.0f;
        if(expenseInput.expenseSplit() != null) {
            for (ExpenseSplitInputDTO expenseSplitInput : expenseInput.expenseSplit()) {
                User user = userRepository.findById(expenseSplitInput.userId()).orElseThrow(() -> new EntityNotFoundException("No such user exists"));
                ExpenseSplit expenseSplit = expenseSplitRepository.findExpenseSplitByExpenseAndUser(expense, user);
                if (expenseSplit != null) {
                    expenseSplit.setAmount(expenseSplitInput.amount());
                } else {
                    expenseSplit = new ExpenseSplit(expense, user, expenseSplitInput.amount(), expense.getName());
                }
                expenseSplitList.add(expenseSplit);
                totalSplitSum += expenseSplit.getAmount();
            }
        }
        assert totalSplitSum.equals(expense.getAmount()) : new AssertionError("Split Expenses sum does not match total expense sum");
        expenseSplitRepository.saveAll(expenseSplitList);

        return expense;
    }

    public Boolean deleteExpense(UUID expenseId){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new EntityNotFoundException("No such expense exists"));
        expenseRepository.delete(expense);
        return true;
    }
}
