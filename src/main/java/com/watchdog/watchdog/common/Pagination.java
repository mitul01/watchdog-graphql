package com.watchdog.watchdog.common;

import com.watchdog.watchdog.model.enums.SortDirection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class Pagination {

    public PageRequest createPageable(String sortField, SortDirection sortDirection, int limit) {
        Sort sort = null;
        if (sortField != null && !sortField.isEmpty()) {
            sort = Sort.by(sortDirection == SortDirection.ASC? Sort.Order.asc(sortField) : Sort.Order.desc(sortField));
        }
        return sort == null ? PageRequest.of(0, limit) : PageRequest.of(0, limit, sort);
    }

}
