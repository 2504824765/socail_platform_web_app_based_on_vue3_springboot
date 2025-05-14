package com.cz.springboot_demo.exception;

// Since 2025/5/12 by CZ
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
