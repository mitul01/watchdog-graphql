package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.common.Pagination;
import com.watchdog.watchdog.common.FilterSpecifications;
import com.watchdog.watchdog.common.FilterValidator;
import com.watchdog.watchdog.dto.BotInputDTO;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.model.Bot;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.enums.SortDirection;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BotService {

    private final BotRepository botRepository;
    @Autowired
    private FilterValidator<Bot> filterValidator;
    @Autowired
    private Pagination pagination;

    public BotService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public Iterable<Bot> getBotsByCriteria(List<FieldFilter> filterMap, String sortField, SortDirection sortDirection, int limit) {
        filterValidator.isFilterValid(filterMap, Bot.class);
        return botRepository.findAll(FilterSpecifications.buildSpecification(filterMap, Bot.class),
                pagination.createPageable(sortField, sortDirection, limit)
        );
    }
    public Bot createBot(BotInputDTO botInputDTO){
        Bot bot = new Bot(botInputDTO.name().toUpperCase());
        botRepository.save(bot);
        return bot;
    }

    public Bot updateBot(UUID botId, BotInputDTO botInputDTO){
        Bot bot = botRepository.findById(botId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("bot")));
        bot.setName(botInputDTO.name().toUpperCase());
        botRepository.save(bot);
        return bot;
    }

    public Boolean deleteBot(UUID botId){
        Bot bot = botRepository.findById(botId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("bot")));
        botRepository.delete(bot);
        return true;
    }

}
