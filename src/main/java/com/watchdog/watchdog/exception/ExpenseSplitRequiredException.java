package com.watchdog.watchdog.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseSplitRequiredException extends RuntimeException{

    private Map<String, Object> parameters = new HashMap<>();

    public ExpenseSplitRequiredException(String message) {
        super(message);
    }

    public ExpenseSplitRequiredException(String message, Map<String, Object> additionParams) {
        this(message);
        if (additionParams != null) {
            parameters = additionParams;
        }
    }

    public Map<String, Object> getExtensions() {
        return parameters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
