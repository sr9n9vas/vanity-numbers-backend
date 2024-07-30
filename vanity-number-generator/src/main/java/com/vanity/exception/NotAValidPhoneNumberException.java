package com.vanity.exception;

public class NotAValidPhoneNumberException extends RuntimeException {
    public NotAValidPhoneNumberException(String message) {
        super(message);
    }
}
