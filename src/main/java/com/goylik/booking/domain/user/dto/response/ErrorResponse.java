package com.goylik.booking.domain.user.dto.response;

public record ErrorResponse(
        String error,
        int status,
        String message
) {
}
