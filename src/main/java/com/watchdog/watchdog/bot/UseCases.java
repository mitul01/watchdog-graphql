package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.dto.BotInputDTO;
import com.watchdog.watchdog.model.Bot;

import java.util.UUID;

public interface UseCases {
    Iterable<Bot> bots();
    Bot createBot(BotInputDTO botInput);
    Bot updateBot(UUID botId, BotInputDTO botInput);
    Boolean deleteBot(UUID botId);
}
