package com.goylik.booking.domain.reservation.service;

import com.goylik.booking.domain.reservation.dto.request.CreateReservationRequest;
import com.goylik.booking.domain.reservation.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService {
    ReservationResponse createReservation(Long userId, CreateReservationRequest request);
    ReservationResponse cancelReservation(Long userId, Long reservationId);
    List<ReservationResponse> getUserReservations(Long userId);
}
