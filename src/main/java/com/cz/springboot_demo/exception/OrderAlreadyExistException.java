package com.cz.springboot_demo.exception;

public class OrderAlreadyExistException extends RuntimeException {
    public OrderAlreadyExistException(String message) {
        super(message);
    }
}
