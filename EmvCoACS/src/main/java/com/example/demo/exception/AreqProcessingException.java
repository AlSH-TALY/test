package com.example.demo.exception;

public class AreqProcessingException extends RuntimeException {
    public AreqProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
