package com.goylik.booking.domain.reservation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record CreateReservationRequest (
        @JsonProperty("resource_id") @NotNull Long resourceId,
        @JsonProperty("start_time") @NotNull Instant startTime,
        @JsonProperty("end_time") @NotNull Instant endTime
) {
}
