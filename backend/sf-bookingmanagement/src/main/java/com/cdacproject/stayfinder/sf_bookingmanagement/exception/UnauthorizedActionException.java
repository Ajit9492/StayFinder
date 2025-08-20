package com.cdacproject.stayfinder.sf_bookingmanagement.exception;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String message) {
        super(message);
    }
}
