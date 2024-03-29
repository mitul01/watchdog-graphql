package com.watchdog.watchdog.expense;

import com.watchdog.watchdog.dto.ExpenseInputDTO;
import com.watchdog.watchdog.dto.ExpenseOwedDTO;
import com.watchdog.watchdog.model.Expense;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.enums.SortDirection;

import java.util.List;
import java.util.UUID;

public interface UseCases {
    Iterable<Expense> expenses(List<FieldFilter> filter, String sortField, SortDirection sortDirection, int limit);
    Expense createExpense(ExpenseInputDTO expenseInput);
    Expense updateExpense(UUID expenseId, ExpenseInputDTO expenseInput);
    Boolean deleteExpense(UUID expenseId);
    Iterable<ExpenseOwedDTO> expenseOwed(UUID accountId, UUID userId);
}
