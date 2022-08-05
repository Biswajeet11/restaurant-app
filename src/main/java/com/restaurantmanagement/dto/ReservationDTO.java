package com.restaurantmanagement.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReservationDTO {
    private Long id;
    @NotNull
    private String restaurantName;
    @NotNull
    private DateDTO dt;
    @NotNull
    private int partySize;
    @NotNull
    private String username;
}
