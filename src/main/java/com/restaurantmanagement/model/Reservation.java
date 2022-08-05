package com.restaurantmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "restaurant_name")
    @NotBlank(message = "Restaurant name is mandatory")
    private String restaurantName;

    @Column(name = "reservation_date")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;


    @Min(value = 1, message = "Minimum person should be 1")
    @Column(name = "party_size", nullable = false)
    private int partySize;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Reservation(String restaurantName,
                       LocalDate reservationDate,
                       int partySize,
                       User user) {
        this.restaurantName = restaurantName;
        this.reservationDate = reservationDate;
        this.partySize = partySize;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = LocalDate.parse(reservationDate);
    }


    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
