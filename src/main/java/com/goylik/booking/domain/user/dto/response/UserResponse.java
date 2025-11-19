package com.goylik.booking.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        Long id,
        String email,
        @JsonProperty("full_name") String fullName
) {
}
