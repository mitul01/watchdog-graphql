package com.watchdog.watchdog.expense;

import com.watchdog.watchdog.model.Expense;
import com.watchdog.watchdog.model.ExpenseSplit;
import com.watchdog.watchdog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, UUID> {
    ExpenseSplit findExpenseSplitByExpenseAndUser(Expense expense, User user);
    Iterable<ExpenseSplit> findAllExpenseSplitByExpense_Account_AccountIdAndExpense_PaidBy_UserIdAndUser_UserId(UUID accountId, UUID paidByUserId, UUID userId);
}
