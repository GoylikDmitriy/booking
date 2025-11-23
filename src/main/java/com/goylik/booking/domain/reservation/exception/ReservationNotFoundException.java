package com.goylik.booking.domain.reservation.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException() {
    }

    public ReservationNotFoundException(Long id) {
        super("Reservation with id = " + id + " is not found.");
    }

    public ReservationNotFoundException(String message) {
        super(message);
    }

    public ReservationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservationNotFoundException(Throwable cause) {
        super(cause);
    }
}
