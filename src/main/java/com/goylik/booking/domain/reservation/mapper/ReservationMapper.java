package com.goylik.booking.domain.reservation.mapper;

import com.goylik.booking.domain.reservation.dto.response.ReservationResponse;
import com.goylik.booking.domain.reservation.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationResponse toResponse(Reservation reservation);
}
