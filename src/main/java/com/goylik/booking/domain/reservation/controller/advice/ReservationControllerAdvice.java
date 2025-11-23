package com.goylik.booking.domain.reservation.controller.advice;

import com.goylik.booking.domain.reservation.exception.InvalidReservationTimeException;
import com.goylik.booking.domain.reservation.exception.ReservationAccessDeniedException;
import com.goylik.booking.domain.reservation.exception.ReservationNotFoundException;
import com.goylik.booking.domain.reservation.exception.ReservationOverlappingException;
import com.goylik.booking.domain.reservation.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ReservationControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    public ErrorResponse handleReservationNotFoundException(ReservationNotFoundException ex) {
        log.warn("Reservation not found: {}", ex.getMessage());
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ReservationAccessDeniedException.class)
    public ErrorResponse handleReservationAccessDeniedException(ReservationAccessDeniedException ex) {
        log.warn("The user does not have access to this reservation: {}", ex.getMessage());
        return new ErrorResponse(
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidReservationTimeException.class)
    public ErrorResponse handleInvalidReservationTimeException(InvalidReservationTimeException ex) {
        log.warn("Invalid reservation time is selected: {}", ex.getMessage());
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ReservationOverlappingException.class)
    public ErrorResponse handleReservationOverlappingException(ReservationOverlappingException ex) {
        log.warn("Reservation overlapping: {}", ex.getMessage());
        return new ErrorResponse(
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public ErrorResponse handleDataAccessException(DataAccessException ex) {
        log.error("Data access error: {}", ex.getMessage(), ex);
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error while accessing database: " + ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return new ErrorResponse(
                "Validation failed",
                HttpStatus.BAD_REQUEST.value(),
                String.join("; ", errors)
        );
    }
}
