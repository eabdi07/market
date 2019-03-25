package com.kata.exception;

public class BadRequestException extends MarketException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
