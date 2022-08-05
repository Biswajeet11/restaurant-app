package com.restaurantmanagement.controller;

import com.restaurantmanagement.dto.ReservationDTO;
import com.restaurantmanagement.exception.ResourceNotFoundException;
import com.restaurantmanagement.model.Reservation;
import com.restaurantmanagement.response.ResponseHandler;
import com.restaurantmanagement.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/reservations")

public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping()
    public ResponseEntity<List<ReservationDTO>> getAllReservations(){
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<Object> getReservationById(@PathVariable(name = "reservationId") Long reservationId){
            ReservationDTO reservationDTO = reservationService.getReservationById(reservationId);
            return  ResponseHandler.generateResponse(reservationDTO,HttpStatus.OK);
    }

    @PostMapping()
    public  ResponseEntity<Object> createReservation(@Valid @RequestBody Reservation reservation){
        reservationService.addReservation(reservation);
        ReservationDTO reservationDTO = reservationService.getReservationById(reservation.getId());
        return ResponseHandler.generateResponse(reservationDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{reservationId}")
    public ResponseEntity<Object> updateReservationById(@PathVariable Long reservationId, @Valid @RequestBody Reservation reservationDetail) {
        ReservationDTO  reservationDTO =  reservationService.updateReservation(reservationDetail,reservationId);
        return ResponseHandler.generateResponse(reservationDTO,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{reservationId}")
    public ResponseEntity<Object> deleteReservationById(@PathVariable(name = "reservationId") Long reservationId) throws ResourceNotFoundException  {
        Long id = reservationService.deleteReservationById(reservationId);
        return ResponseHandler.generateResponse("Reservation deleted :" + id, HttpStatus.ACCEPTED);
    }
}
