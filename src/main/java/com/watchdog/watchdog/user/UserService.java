package com.watchdog.watchdog.user;

import com.watchdog.watchdog.bot.BotRepository;
import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.Bot;
import com.watchdog.watchdog.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BotRepository botRepository;

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(UserInputDTO userInput){
        Set<Bot> installedBots = new HashSet<>();
        installedBots.add(botRepository.findById(userInput.botId()).orElseThrow(() -> new EntityNotFoundException("No such bot exists")));
        User user = new User(userInput.userName(),userInput.firstName(),userInput.lastName(),installedBots);
        userRepository.save(user);
        return user;
    }

}
