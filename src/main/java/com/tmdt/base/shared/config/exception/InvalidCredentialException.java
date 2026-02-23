package com.tmdt.base.shared.config.exception;

public class InvalidCredentialException extends RuntimeException {

    public InvalidCredentialException() {
        super("Invalid username or password");
    }
}

