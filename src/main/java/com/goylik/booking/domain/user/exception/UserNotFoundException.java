package com.goylik.booking.domain.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(Long id) {
        super("User with id = " + id + " is not found.");
    }

    public UserNotFoundException(String email) {
        super("User with email: " + email + " is not found.");
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
