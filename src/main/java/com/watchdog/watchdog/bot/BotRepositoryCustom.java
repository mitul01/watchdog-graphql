package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.model.Bot;
import com.watchdog.watchdog.model.FieldFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BotRepositoryCustom {

    Iterable<Bot> findBotsByCriteria(List<FieldFilter> filterMap, Pageable pageable);


}
