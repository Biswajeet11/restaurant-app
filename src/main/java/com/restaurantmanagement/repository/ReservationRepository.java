package com.restaurantmanagement.repository;

import com.restaurantmanagement.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findReservationById(Long reservationId);
    List<Reservation> getReservationByUserId(Long userId);

    void deleteById(Long reservationId);


//    List<Reservation> findAllReservationByDate(LocalDate date);

//    List<Reservation> getReservationByDateTimeAndCustomerId(LocalDate date, LocalTime time, Long customerId);
}
