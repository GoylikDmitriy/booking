package com.goylik.booking.domain.reservation.exception;

public class InvalidReservationTimeException extends RuntimeException {
    public InvalidReservationTimeException() {
    }

    public InvalidReservationTimeException(String message) {
        super(message);
    }

    public InvalidReservationTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReservationTimeException(Throwable cause) {
        super(cause);
    }
}
