package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.dto.BotInputDTO;
import com.watchdog.watchdog.model.Bot;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.enums.SortDirection;

import java.util.List;
import java.util.UUID;

public interface UseCases {
    Iterable<Bot> bots(List<FieldFilter> filter, String sortField, SortDirection sortDirection, int limit);
    Bot createBot(BotInputDTO botInput);
    Bot updateBot(UUID botId, BotInputDTO botInput);
    Boolean deleteBot(UUID botId);
}
