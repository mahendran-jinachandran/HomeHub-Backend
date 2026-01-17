package com.homehub.core.login.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super("User " + message + " already exists");
    }
}
