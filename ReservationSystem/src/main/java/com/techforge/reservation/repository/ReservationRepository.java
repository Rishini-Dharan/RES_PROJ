package com.techforge.reservation.repository;

import com.techforge.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("""
        SELECT COUNT(r) > 0
        FROM Reservation r
        WHERE r.table.id = :tableId
        AND r.status <> 'CANCELLED'
        AND r.startTime < :endTime
        AND r.endTime > :startTime
    """)
    boolean checkForBooking(@Param("tableId") long tableId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
