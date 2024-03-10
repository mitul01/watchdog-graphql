package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.dto.BotInputDTO;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.model.Bot;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.enums.SortDirection;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BotService {

    private final BotRepository botRepository;

    public BotService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public Iterable<Bot> getBotsByCriteria(List<FieldFilter> filterMap, String sortField, SortDirection sortDirection, int limit) {
        isFilterValid(filterMap);
        Sort sort = Sort.by(sortDirection == SortDirection.ASC? Sort.Order.asc(sortField) : Sort.Order.desc(sortField));
        return botRepository.findAll(
                BotSpecifications.buildSpecification(filterMap),
                PageRequest.of(0, limit, sort)
        );
    }

    private void isFilterValid(List<FieldFilter> filterMap) {
        List<String> botFields = new Bot().getAllFieldNames();
        if (filterMap != null && !filterMap.isEmpty()) {
            filterMap.forEach(item -> {
                if (!botFields.contains(item.getFieldName())) {
                    throw new IllegalArgumentException("Invalid fieldName: " + item.getFieldName());
                }
            });
        }
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
