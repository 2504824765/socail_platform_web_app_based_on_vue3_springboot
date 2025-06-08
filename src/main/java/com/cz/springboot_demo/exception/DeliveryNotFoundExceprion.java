package com.cz.springboot_demo.exception;

public class DeliveryNotFoundExceprion extends RuntimeException {
    public DeliveryNotFoundExceprion(String message) {
        super(message);
    }
}
