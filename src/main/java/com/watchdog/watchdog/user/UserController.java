package com.watchdog.watchdog.user;

import com.watchdog.watchdog.bot.BotController;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserController implements UseCases {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @QueryMapping
    public Iterable<User> users() {
        logger.info(Constants.getRequestInfoLogMsg.formatted("user"));
        return userService.getUsers();
    }

    @MutationMapping
    public User createUser(@Argument UserInputDTO user){
        logger.info(Constants.createRequestInfoLogMsg.formatted("user",user.toString()));
        return userService.createUser(user);
    }

    @MutationMapping
    public User updateUser(@Argument UUID userId,@Argument UserInputDTO user){
        logger.info(Constants.updateRequestInfoLogMsg.formatted("user",userId.toString(),user.toString()));
        return userService.updateUser(userId,user);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument UUID userId){
        logger.info(Constants.deleteRequestInfoLogMsg.formatted("user",userId.toString()));
        return userService.deleteUser(userId);
    }

}
