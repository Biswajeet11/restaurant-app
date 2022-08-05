package com.restaurantmanagement.service;

import com.restaurantmanagement.dto.DateDTO;
import com.restaurantmanagement.dto.ReservationDTO;
import com.restaurantmanagement.exception.ResourceNotFoundException;
import com.restaurantmanagement.model.Reservation;
import com.restaurantmanagement.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public ReservationDTO convertEntityToDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        DateDTO dateDTO = new DateDTO();
        LocalDate date = reservation.getReservationDate();
        int month = date.getDayOfMonth();
        String day =  date.getDayOfWeek().toString();
        int dayOfYear = date.getDayOfYear();


        dateDTO.setDayOfYear(dayOfYear);
        dateDTO.setDayOfWeek(day);
        dateDTO.setDayOfMonth(month);


        reservationDTO.setId(reservation.getId());
        reservationDTO.setRestaurantName(reservation.getRestaurantName());
        reservationDTO.setPartySize(reservation.getPartySize());
        reservationDTO.setDt(dateDTO);
        reservationDTO.setUsername(reservation.getUser().getUserName());

        return reservationDTO;
    }

    public List<ReservationDTO> getAllReservations(){

        return reservationRepository.findAll()
                .stream().map(this:: convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public ReservationDTO getReservationById(Long reservationId)  {

        Reservation reservation =  reservationRepository.findReservationById(reservationId);
        if(Objects.isNull(reservation)){
            throw new ResourceNotFoundException("Reservation Not found for reservationId: "+reservationId);
        }
         return convertEntityToDTO(reservation);

    }

    public List<Reservation> getReservationByUserId(Long userId){
        return reservationRepository.getReservationByUserId(userId);
    }

    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }


    public Long deleteReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findReservationById(reservationId);
        if(Objects.isNull(reservation)){
            throw new ResourceNotFoundException("Reservation Not found for reservationId: "+reservationId);
        }
        else {
            reservationRepository.deleteById(reservationId);
            return reservation.getId();
        }
    }

    public ReservationDTO updateReservation(Reservation reservationData, Long reservationId) {
        Reservation reservationValue = reservationRepository.findReservationById(reservationId);
        if(Objects.isNull(reservationValue)){
            throw new ResourceNotFoundException("Reservation Not found for reservationId: "+reservationId);
        }
         else  {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                reservationValue.setReservationDate(reservationData.getReservationDate().format(formatter));
                reservationValue.setPartySize(reservationData.getPartySize());
                reservationValue.setRestaurantName(reservationData.getRestaurantName());
                reservationValue.setUser(reservationData.getUser());
                reservationRepository.save(reservationValue);

                return convertEntityToDTO(reservationValue);
            }

    }
}
