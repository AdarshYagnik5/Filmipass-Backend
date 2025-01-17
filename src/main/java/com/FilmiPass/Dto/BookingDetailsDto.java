package com.FilmiPass.Dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookingDetailsDto {
    private String movieName;
    private String theaterName;
    private String city;
    private int numberOfSeats;
    private double totalPrice;
    private LocalDateTime bookingTime;
    private String seatNumbers;

    private String showDate;

    private String showTime;

}
