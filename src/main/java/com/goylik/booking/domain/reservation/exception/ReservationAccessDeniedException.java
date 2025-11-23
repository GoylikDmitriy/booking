package com.goylik.booking.domain.reservation.exception;

public class ReservationAccessDeniedException extends RuntimeException {
    public ReservationAccessDeniedException() {
        super("You can't cancel this reservation.");
    }

    public ReservationAccessDeniedException(String message) {
        super(message);
    }

    public ReservationAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservationAccessDeniedException(Throwable cause) {
        super(cause);
    }
}
