package com.goylik.booking.domain.reservation.exception;

public class ReservationOverlappingException extends RuntimeException {
    public ReservationOverlappingException() {
        super("Requested time slot is already taken.");
    }

    public ReservationOverlappingException(String message) {
        super(message);
    }

    public ReservationOverlappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservationOverlappingException(Throwable cause) {
        super(cause);
    }
}
