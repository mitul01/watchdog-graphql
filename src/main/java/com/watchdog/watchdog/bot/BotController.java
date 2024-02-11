package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.dto.BotInputDTO;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.model.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(BotController.class);

    @QueryMapping
    public Iterable<Bot> bots(){
        logger.info(Constants.getRequestInfoLogMsg.formatted("bot"));
        return botService.getUsers();
    }

    @MutationMapping
    public Bot createBot(@Argument BotInputDTO bot){
        logger.info(Constants.createRequestInfoLogMsg.formatted("bot",bot.toString()));
        return botService.createBot(bot);
    }

    @MutationMapping
    public Bot updateBot(@Argument UUID botId, @Argument BotInputDTO bot){
        logger.info(Constants.updateRequestInfoLogMsg.formatted("bot",botId.toString(),bot.toString()));
        return botService.updateBot(botId,bot);
    }

    @MutationMapping
    public Boolean deleteBot(@Argument UUID botId){
        logger.info(Constants.deleteRequestInfoLogMsg.formatted("bot",botId.toString()));
        return botService.deleteBot(botId);
    }

}
