package com.goylik.booking.domain.reservation.repository;

import com.goylik.booking.domain.reservation.model.Reservation;
import com.goylik.booking.domain.reservation.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("""
            SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END
            FROM Reservation r
            WHERE r.resourceId = :resourceId 
            AND r.status = :activeStatus 
            AND r.startTime < :end 
            AND r.endTime > :start
            """)
    boolean existsOverlapping(
            @Param("resourceId") Long resourceId,
            @Param("start") Instant start,
            @Param("end") Instant end,
            @Param("activeStatus")ReservationStatus activeStatus);

    List<Reservation> findAllByUserId(Long userId);
}
