package com.watchdog.watchdog.expense;

import com.watchdog.watchdog.account.AccountRepository;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.dto.ExpenseInputDTO;
import com.watchdog.watchdog.dto.ExpenseOwedDTO;
import com.watchdog.watchdog.dto.ExpenseSplitInputDTO;
import com.watchdog.watchdog.exception.ExpenseSplitRequiredException;
import com.watchdog.watchdog.model.Account;
import com.watchdog.watchdog.model.Expense;
import com.watchdog.watchdog.model.ExpenseSplit;
import com.watchdog.watchdog.model.User;
import com.watchdog.watchdog.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Account account = accountRepository.findById(expenseInput.accountId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("account")));
        User paidByUser = null;
        for(User user: account.getUsers()){
            if(user.getUserId().equals(expenseInput.paidByUserId())){
                 paidByUser = user;
                 break;
            }
        }
        if(paidByUser == null){
            throw new EntityNotFoundException(Constants.userNotFoundInAccountErrorMsg);
        }
        Expense expense = new Expense(expenseInput.name(),expenseInput.amount(),account,paidByUser);

        // Update expense split table
        List<ExpenseSplit> expenseSplitList = new ArrayList<>();
        Float totalSplitSum = 0.0f;
        if(expense.getAccount().getUsers().size() > 1) {
            if(expenseInput.expenseSplit() != null) {
                for (ExpenseSplitInputDTO expenseSplitInput : expenseInput.expenseSplit()) {
                    User user = userRepository.findById(expenseSplitInput.userId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("user")));
                    ExpenseSplit expenseSplit = new ExpenseSplit(expense, user, expenseSplitInput.amount(), expense.getName());
                    expenseSplitList.add(expenseSplit);
                    totalSplitSum += expenseSplit.getAmount();
                }
                assert totalSplitSum.equals(expense.getAmount()) : new AssertionError(Constants.expenseSplitSumNotMatchErrorMsg);
                expenseRepository.save(expense);
                expenseSplitRepository.saveAll(expenseSplitList);
            } else {
               throw new ExpenseSplitRequiredException(Constants.expenseSplitRequiredErrorMsg);
            }
        } else {
            expenseRepository.save(expense);
        }

        return expense;

    }

    public Expense updateExpense(UUID expenseId, ExpenseInputDTO expenseInput){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("expense")));
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
                throw new EntityNotFoundException(Constants.userNotFoundInAccountErrorMsg);
            }
        }
        expenseRepository.save(expense);

        // Update expense split table
        List<ExpenseSplit> expenseSplitList = new ArrayList<>();
        Float totalSplitSum = 0.0f;
        if(expenseInput.expenseSplit() != null) {
            for (ExpenseSplitInputDTO expenseSplitInput : expenseInput.expenseSplit()) {
                User user = userRepository.findById(expenseSplitInput.userId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("user")));
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
        assert totalSplitSum.equals(expense.getAmount()) : new AssertionError(Constants.expenseSplitSumNotMatchErrorMsg);
        expenseSplitRepository.saveAll(expenseSplitList);

        return expense;
    }

    public Boolean deleteExpense(UUID expenseId){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("expense")));
        expenseRepository.delete(expense);
        return true;
    }

    public Float getTotalOwedAmount(UUID accountId, UUID userId1, UUID userId2){
        Float totalAmt = 0.0f;
        for (ExpenseSplit expense: expenseSplitRepository.findAllExpenseSplitByExpense_Account_AccountIdAndExpense_PaidBy_UserIdAndUser_UserId(accountId,userId1,userId2)){
            totalAmt += expense.getAmount();
        }
        return totalAmt;
    }

    public Iterable<ExpenseOwedDTO> expenseOwed(UUID accountId, UUID userId){
        Iterable<Expense> expenses = expenseRepository.findAllByAccount_AccountIdAndPaidBy_UserId(accountId,userId);
        HashMap<UUID,ExpenseOwedDTO> expensesOwed = new HashMap<>();
        for(Expense expense: expenses){
            for(ExpenseSplit expenseSplit: expense.getExpenseSplit()){
                if(!expenseSplit.getUser().getUserId().equals(userId)) {
                    if(expensesOwed.get(expenseSplit.getUser().getUserId()) != null){
                        expensesOwed.put(expenseSplit.getUser().getUserId(),new ExpenseOwedDTO(expenseSplit.getUser(),expensesOwed.get(expenseSplit.getUser().getUserId()).owedAmount() + expenseSplit.getAmount()));
                    } else {
                        expensesOwed.put(expenseSplit.getUser().getUserId(),new ExpenseOwedDTO(expenseSplit.getUser(),expenseSplit.getAmount()));
                    }
                }
            }
        }
        for(ExpenseOwedDTO expenseOwed: expensesOwed.values()){
            expensesOwed.put(expenseOwed.user().getUserId(),new ExpenseOwedDTO(expenseOwed.user(),getTotalOwedAmount(accountId,expenseOwed.user().getUserId(),userId) - expenseOwed.owedAmount()));
        }
        return new ArrayList<>(expensesOwed.values());
    }
}
