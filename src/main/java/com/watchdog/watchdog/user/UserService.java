package com.watchdog.watchdog.user;

import com.watchdog.watchdog.account.AccountRepository;
import com.watchdog.watchdog.account.AccountService;
import com.watchdog.watchdog.bot.BotRepository;
import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.Bot;
import com.watchdog.watchdog.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BotRepository botRepository;

    @Autowired
    private AccountService accountService;

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(UserInputDTO userInput){
        Set<Bot> installedBots = new HashSet<>();
        installedBots.add(botRepository.findById(userInput.botId()).orElseThrow(() -> new EntityNotFoundException("No such bot exists")));
        User user = new User(userInput.userName(),userInput.firstName(),userInput.lastName(),installedBots);
        userRepository.save(user);
        accountService.createDefaultAccount(user);
        return user;
    }

    public User updateUser(UUID userId, UserInputDTO userInput){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("No such user exists"));
        if (userInput.userName()  != null) user.setUserName(userInput.userName());
        if (userInput.firstName()  != null) user.setFirstName(userInput.firstName());
        if (userInput.lastName()  != null) user.setLastName(userInput.lastName());
        if (userInput.botId()  != null) {
            Set<Bot> installedBots = user.getInstalledBots();
            installedBots.add(botRepository.findById(userInput.botId()).orElseThrow(() -> new EntityNotFoundException("No such bot exists")));
            user.setInstalledBots(installedBots);
        }
        userRepository.save(user);
        return user;
    }

    public Boolean deleteUser(UUID userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("No such bot exists"));
        userRepository.delete(user);
        return true;
    }

}
