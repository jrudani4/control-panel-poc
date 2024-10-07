package com.cp.exception.exceptions;

public class NoRecordsFoundException extends RuntimeException {
    public NoRecordsFoundException(String message) {
        super(message);
    }
}
