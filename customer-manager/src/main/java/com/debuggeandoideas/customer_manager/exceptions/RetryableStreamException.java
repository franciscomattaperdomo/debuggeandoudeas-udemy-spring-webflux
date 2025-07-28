package com.debuggeandoideas.customer_manager.exceptions;

public class RetryableStreamException extends RuntimeException {

    public RetryableStreamException(String message) {
        super(message);
    }
}
