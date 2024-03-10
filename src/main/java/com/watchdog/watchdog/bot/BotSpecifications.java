package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.model.Bot;
import com.watchdog.watchdog.model.FieldFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BotSpecifications {

    public static Specification<Bot> buildSpecification(List<FieldFilter> filterMap) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filterMap != null && !filterMap.isEmpty()) {
                filterMap.forEach(item -> {
                    if (item.getFieldValue() != null && !item.getFieldValue().isEmpty()) {
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(item.getFieldName())),  item.getFieldValue().toLowerCase()));
                    }
                });
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
