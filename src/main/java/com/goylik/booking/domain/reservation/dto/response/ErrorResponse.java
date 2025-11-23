package com.goylik.booking.domain.reservation.dto.response;

public record ErrorResponse(
        String error,
        int status,
        String message
) {
}
