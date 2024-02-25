package com.watchdog.watchdog.expense;

import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.dto.ExpenseInputDTO;
import com.watchdog.watchdog.dto.ExpenseOwedDTO;
import com.watchdog.watchdog.model.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @QueryMapping
    public Iterable<Expense> expenses() {
        logger.info(Constants.getRequestInfoLogMsg.formatted("expense"));
        return expenseService.getExpenses();
    }

    @QueryMapping
    public Iterable<ExpenseOwedDTO> expenseOwed(@Argument UUID accountId, @Argument UUID userId) {
        logger.info(Constants.getRequestInfoLogMsg.formatted("expense owed data"));
        return expenseService.expenseOwed(accountId,userId);
    }

    @MutationMapping
    public Expense createExpense(@Argument ExpenseInputDTO expense){
        logger.info(Constants.createRequestInfoLogMsg.formatted("expense",expense.toString()));
        return expenseService.createExpense(expense);
    }

    @MutationMapping
    public Expense updateExpense(@Argument UUID expenseId, @Argument ExpenseInputDTO expense){
        logger.info(Constants.updateRequestInfoLogMsg.formatted("expense",expense.toString(),expense.toString()));
        return expenseService.updateExpense(expenseId,expense);
    }

    @MutationMapping
    public Boolean deleteExpense(@Argument UUID expenseId){
        logger.info(Constants.deleteRequestInfoLogMsg.formatted("expense",expenseId.toString()));
        return expenseService.deleteExpense(expenseId);
    }

}
