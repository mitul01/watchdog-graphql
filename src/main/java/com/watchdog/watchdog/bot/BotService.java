package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.dto.BotInputDTO;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.model.Bot;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BotService {

    @Autowired
    private BotRepository botRepository;

    public Iterable<Bot> getUsers(){
        return botRepository.findAll();
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
