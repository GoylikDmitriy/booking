package com.goylik.booking.domain.reservation.controller;

import com.goylik.booking.domain.reservation.dto.request.CreateReservationRequest;
import com.goylik.booking.domain.reservation.dto.response.ReservationResponse;
import com.goylik.booking.domain.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ReservationResponse createReservation(@RequestParam("userId") Long userId,
                                                 @Valid @RequestBody CreateReservationRequest request) {
        return reservationService.createReservation(userId, request);
    }

    @DeleteMapping("/{id}")
    public ReservationResponse cancelReservation(@PathVariable Long id,
                                                 @RequestParam("userId") Long userId) {
        return reservationService.cancelReservation(userId, id);
    }

    @GetMapping("/user/{userId}")
    public List<ReservationResponse> getUserReservations(@PathVariable Long userId) {
        return reservationService.getUserReservations(userId);
    }
}
