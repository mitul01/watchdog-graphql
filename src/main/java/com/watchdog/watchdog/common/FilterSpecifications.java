package com.watchdog.watchdog.common;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;

import com.watchdog.watchdog.model.FieldFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class FilterSpecifications {

    public static <T> Specification<T> buildSpecification(List<FieldFilter> filterMap, Class<T> entityClass) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filterMap != null && !filterMap.isEmpty()) {
                filterMap.forEach(item -> {
                    if (item.getFieldValue() != null) {
                        Class<?> fieldType = getFieldType(entityClass, item.getFieldName());
                        if (fieldType == Integer.class || fieldType == int.class) {
                            predicates.add(criteriaBuilder.equal(root.get(item.getFieldName()),
                                    Integer.parseInt(item.getFieldValue())));
                        } else if (fieldType == Float.class || fieldType == float.class) {
                            predicates.add(criteriaBuilder.equal(root.get(item.getFieldName()),
                                    Float.parseFloat(item.getFieldValue())));
                        } else if (fieldType == String.class) {
                            predicates.add(criteriaBuilder.like(root.get(item.getFieldName()),
                                    item.getFieldValue()));
                        }
                    }
                });
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Class<?> getFieldType(Class<?> entityClass, String fieldName) {
        try {
            Field field = entityClass.getDeclaredField(fieldName);
            return field.getType();
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Invalid fieldName: " + fieldName);
        }
    }
}
