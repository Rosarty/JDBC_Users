package org.example.app.exceptions;

import java.util.Map;

public class UserException extends RuntimeException {

    private Map<String, String> errors;

    public UserException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public String getErrors() {
        StringBuilder sb = new StringBuilder();
        errors.forEach((key, value) ->
                sb.append(String.format("%n>> %s: %s", key, value))
        );
        return sb.toString();
    }
}
