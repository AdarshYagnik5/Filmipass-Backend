package com.FilmiPass.Modal;

import com.FilmiPass.utils.CommaSeparatedListDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "booking-details")
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;


    @Column(nullable = false)
    private String movieName;

    @Column(nullable = false)
    private String showDate;

    @Column(nullable = false)
    private String showTime;

    @Column(nullable = false)
    private String theaterName;

    private String city;

    @Column(nullable = false)
    private int numberOfSeats;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column(nullable = false)
    @JsonDeserialize
    private List<String> seatNumbers;
}
