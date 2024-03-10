package com.watchdog.watchdog.bot;

import com.watchdog.watchdog.model.Bot;
import com.watchdog.watchdog.model.FieldFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class BotRepositoryImpl implements BotRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public BotRepositoryImpl() {
    }

    @Override
    public Iterable<Bot> findBotsByCriteria(List<FieldFilter> filterMap, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bot> criteriaQuery = criteriaBuilder.createQuery(Bot.class);
        Root<Bot> root = criteriaQuery.from(Bot.class);
        Predicate predicate = criteriaBuilder.conjunction();

        if (filterMap != null && !filterMap.isEmpty()) {
            for (FieldFilter item : filterMap) {
                String key = item.getFieldName();
                String value = item.getFieldValue();
                if (value != null && !value.isEmpty()) {
                    predicate = criteriaBuilder.and(predicate,
                            criteriaBuilder.like(criteriaBuilder.lower(root.get(key)), value.toLowerCase()));

                }
            }
        }

        criteriaQuery.where(predicate);
        TypedQuery<Bot> query = entityManager.createQuery(criteriaQuery);

        if (pageable != null) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        return query.getResultList();
    }

}
