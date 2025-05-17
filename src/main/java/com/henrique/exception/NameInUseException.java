package com.henrique.exception;

public class NameInUseException extends RuntimeException {
    public NameInUseException(String message) {
        super(message);
    }
}
