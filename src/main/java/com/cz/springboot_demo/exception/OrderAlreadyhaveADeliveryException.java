package com.cz.springboot_demo.exception;

public class OrderAlreadyhaveADeliveryException extends RuntimeException {
    public OrderAlreadyhaveADeliveryException(String message) {
        super(message);
    }
}
