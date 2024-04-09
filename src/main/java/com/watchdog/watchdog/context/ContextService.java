package com.watchdog.watchdog.context;

import com.watchdog.watchdog.account.AccountRepository;
import com.watchdog.watchdog.bot.BotRepository;
import com.watchdog.watchdog.common.FilterSpecifications;
import com.watchdog.watchdog.common.FilterValidator;
import com.watchdog.watchdog.common.Pagination;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.dto.ContextInputDTO;
import com.watchdog.watchdog.model.*;
import com.watchdog.watchdog.model.enums.SortDirection;
import com.watchdog.watchdog.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContextService {

    @Autowired
    private ContextRepository contextRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BotRepository botRepository;

    @Autowired
    private FilterValidator<Context> filterValidator;

    @Autowired
    private Pagination pagination;

    public Iterable<Context> getContextsByCriteria(List<FieldFilter> filterMap, String sortField, SortDirection sortDirection, int limit){
        filterValidator.isFilterValid(filterMap, Context.class);
        return contextRepository.findAll(FilterSpecifications.buildSpecification(filterMap, Context.class),
                pagination.createPageable(sortField, sortDirection, limit)
        );
    }

    public Context createContext(ContextInputDTO contextInput){
        Account account = accountRepository.findById(contextInput.accountId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("account")));
        User user = userRepository.findById(contextInput.userId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("user")));
        Bot bot = botRepository.findById(contextInput.botId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("bot")));
        Context context = new Context(contextInput.chatId(),bot,user,account);
        contextRepository.save(context);
        return context;
    }

    public Context updateContext(String chatId, ContextInputDTO contextInput){
        Context context = contextRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("context")));
        Account account = accountRepository.findById(contextInput.accountId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("account")));
        context.setAccount(account);
        contextRepository.save(context);
        return context;
    }

    public Boolean deleteContext(String chatId){
        Context context = contextRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("context")));
        contextRepository.delete(context);
        return true;
    }
}
