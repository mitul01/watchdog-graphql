package com.watchdog.watchdog.user;

import com.watchdog.watchdog.account.AccountService;
import com.watchdog.watchdog.bot.BotRepository;
import com.watchdog.watchdog.common.Pagination;
import com.watchdog.watchdog.common.FilterSpecifications;
import com.watchdog.watchdog.common.FilterValidator;
import com.watchdog.watchdog.dto.Constants;
import com.watchdog.watchdog.dto.UserInputDTO;
import com.watchdog.watchdog.model.Bot;
import com.watchdog.watchdog.model.FieldFilter;
import com.watchdog.watchdog.model.User;
import com.watchdog.watchdog.model.enums.SortDirection;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BotRepository botRepository;
    @Autowired
    private FilterValidator<User> filterValidator;
    @Autowired
    private AccountService accountService;
    @Autowired
    private Pagination pagination;

    public Iterable<User> getUsersByCriteria(List<FieldFilter> filterMap, String sortField, SortDirection sortDirection, int limit){
        filterValidator.isFilterValid(filterMap, User.class);
        return userRepository.findAll(FilterSpecifications.buildSpecification(filterMap, User.class),
                pagination.createPageable(sortField, sortDirection, limit)
        );
    }


    public User createUser(UserInputDTO userInput){
        Set<Bot> installedBots = new HashSet<>();
        installedBots.add(botRepository.findById(userInput.botId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("user"))));
        User user = new User(userInput.userName(),installedBots);
        userRepository.save(user);
        accountService.createDefaultAccount(user);
        return user;
    }

    public User updateUser(UUID userId, UserInputDTO userInput){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("user")));
        if (userInput.userName()  != null) user.setUserName(userInput.userName());
        if (userInput.botId()  != null) {
            Set<Bot> installedBots = user.getInstalledBots();
            installedBots.add(botRepository.findById(userInput.botId()).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("bot"))));
            user.setInstalledBots(installedBots);
        }
        userRepository.save(user);
        return user;
    }

    public Boolean deleteUser(UUID userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(Constants.entityNotFoundErrorMsg.formatted("user")));
        userRepository.delete(user);
        return true;
    }

}
