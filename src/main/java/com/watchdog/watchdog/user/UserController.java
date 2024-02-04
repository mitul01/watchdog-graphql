package com.watchdog.watchdog.user;

import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController implements UseCases {

    @Autowired
    private UserService userService;

    @QueryMapping
    public Iterable<User> users() {
        return userService.getUsers();
    }

    @MutationMapping
    public User createUser(@Argument UserInputDTO user){
        return userService.createUser(user);
    }

}
