package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.dto.BotInputDTO;
import com.watchdog.watchdog.model.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class BotController implements UseCases{

    @Autowired
    private BotService botService;

    @QueryMapping
    public Iterable<Bot> bots(){
        return botService.getUsers();
    }

    @MutationMapping
    public Bot createBot(@Argument BotInputDTO bot){
        return botService.createBot(bot);
    }

    @MutationMapping
    public Bot updateBot(@Argument UUID botId, @Argument BotInputDTO bot){
        return botService.updateBot(botId,bot);
    }

    @MutationMapping
    public Boolean deleteBot(@Argument UUID botId){
        return botService.deleteBot(botId);
    }

}
