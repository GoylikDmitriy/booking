package com.goylik.booking.domain.reservation.service.impl;

import com.goylik.booking.domain.reservation.dto.request.CreateReservationRequest;
import com.goylik.booking.domain.reservation.dto.response.ReservationResponse;
import com.goylik.booking.domain.reservation.exception.ReservationAccessDeniedException;
import com.goylik.booking.domain.reservation.exception.ReservationNotFoundException;
import com.goylik.booking.domain.reservation.exception.ReservationOverlappingException;
import com.goylik.booking.domain.reservation.mapper.ReservationMapper;
import com.goylik.booking.domain.reservation.model.Reservation;
import com.goylik.booking.domain.reservation.model.ReservationStatus;
import com.goylik.booking.domain.reservation.repository.ReservationRepository;
import com.goylik.booking.domain.reservation.service.ReservationService;
import com.goylik.booking.domain.resource.exception.ResourceNotFoundException;
import com.goylik.booking.domain.resource.model.Resource;
import com.goylik.booking.domain.resource.repository.ResourceRepository;
import com.goylik.booking.domain.user.exception.UserNotFoundException;
import com.goylik.booking.domain.user.model.User;
import com.goylik.booking.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ReservationResponse createReservation(Long userId, CreateReservationRequest request) {
        if (reservationRepository.existsOverlapping(request.resourceId(),
                request.startTime(), request.endTime(), ReservationStatus.ACTIVE)) {
            throw new ReservationOverlappingException();
        }

        Reservation reservation = new Reservation(request.startTime(), request.endTime(),
                fetchResourceById(request.resourceId()), fetchUserById(userId), ReservationStatus.ACTIVE);

        reservation = reservationRepository.save(reservation);
        return reservationMapper.toResponse(reservation);
    }

    @Override
    @Transactional
    public ReservationResponse cancelReservation(Long userId, Long reservationId) {
        Reservation reservation = fetchReservationById(reservationId);
        if (!reservation.getUser().getId().equals(userId)) {
            throw new ReservationAccessDeniedException();
        }

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            return reservationMapper.toResponse(reservation);
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        var saved = reservationRepository.save(reservation);

        return reservationMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponse> getUserReservations(Long userId) {
        var reservations = reservationRepository.findAllByUserId(userId);
        return reservations.stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    private Reservation fetchReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    private Resource fetchResourceById(Long id) {
        return resourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private User fetchUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
