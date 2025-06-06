package com.cz.springboot_demo.exception;

public class CategoryLevelIsLowestException extends RuntimeException {
    public CategoryLevelIsLowestException(String message) {
        super(message);
    }
}
