package com.watchdog.watchdog.common;

import com.watchdog.watchdog.model.FieldFilter;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilterValidator<T> {
    public void isFilterValid(List<FieldFilter> filterMap, Class<T> entityClass) {
        List<String> fields = getAllFieldNames(entityClass);
        if (filterMap != null && !filterMap.isEmpty()) {
            filterMap.forEach(item -> {
                if (!fields.contains(item.getFieldName())) {
                    throw new IllegalArgumentException("Invalid fieldName: " + item.getFieldName());
                }
            });
        }
    }
    private List<String> getAllFieldNames(Class<T> entityClass) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
}
