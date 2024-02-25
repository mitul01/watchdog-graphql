package com.watchdog.watchdog.expense;

import com.watchdog.watchdog.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    Iterable<Expense> findAllByAccount_AccountIdAndPaidBy_UserId(UUID accountId, UUID userId);
}
