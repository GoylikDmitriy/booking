package com.goylik.booking.domain.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goylik.booking.domain.reservation.model.ReservationStatus;

import java.time.Instant;

public record ReservationResponse(
        Long id,
        @JsonProperty("resource_id") Long resourceId,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("start_time") Instant startTime,
        @JsonProperty("end_time") Instant endTime,
        ReservationStatus status
        ) {
}
