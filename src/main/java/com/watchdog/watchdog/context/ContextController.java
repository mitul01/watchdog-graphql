package com.watchdog.watchdog.context;

import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.dto.ContextInputDTO;
import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.Context;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.User;
import com.watchdog.watchdog.model.enums.SortDirection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ContextController implements UseCases {

    @Autowired
    private ContextService contextService;

    Logger logger = LoggerFactory.getLogger(ContextController.class);

    @QueryMapping
    public Iterable<Context> contexts(@Argument List<FieldFilter> filter,
                                      @Argument String sortField,
                                      @Argument SortDirection sortDirection,
                                      @Argument int limit) {
        logger.info(Constants.getRequestInfoLogMsg.formatted("context"));
        return contextService.getContextsByCriteria(filter, sortField, sortDirection, limit);
    }

    @MutationMapping
    public Context createContext(@Argument ContextInputDTO context){
        logger.info(Constants.createRequestInfoLogMsg.formatted("context",context.toString()));
        return contextService.createContext(context);
    }

    @MutationMapping
    public Context updateContext(@Argument String chatId, @Argument ContextInputDTO context){
        logger.info(Constants.updateRequestInfoLogMsg.formatted("context",chatId,context.toString()));
        return contextService.updateContext(chatId,context);
    }

    @MutationMapping
    public Boolean deleteContext(@Argument String chatId){
        logger.info(Constants.deleteRequestInfoLogMsg.formatted("context",chatId));
        return contextService.deleteContext(chatId);
    }
}
