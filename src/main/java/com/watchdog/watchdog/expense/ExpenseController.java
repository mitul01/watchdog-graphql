package com.watchdog.watchdog.expense;

import com.watchdog.watchdog.dto.ExpenseInputDTO;
import com.watchdog.watchdog.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ExpenseController implements UseCases {

    @Autowired
    private ExpenseService expenseService;

    @QueryMapping
    public Iterable<Expense> expenses() {
        return expenseService.getExpenses();
    }

    @MutationMapping
    public Expense createExpense(@Argument ExpenseInputDTO expense){
        return expenseService.createExpense(expense);
    }

    @MutationMapping
    public Expense updateExpense(@Argument UUID expenseId, @Argument ExpenseInputDTO expense){
        return expenseService.updateExpense(expenseId,expense);
    }

    @MutationMapping
    public Boolean deleteExpense(@Argument UUID expenseId){
        return expenseService.deleteExpense(expenseId);
    }

}
