package com.henrique.exception;

public class UserDontFindException extends RuntimeException {
    public UserDontFindException(String message) {
        super(message);
    }
}
