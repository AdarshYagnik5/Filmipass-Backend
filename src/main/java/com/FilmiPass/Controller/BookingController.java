package com.FilmiPass.Controller;

import com.FilmiPass.Dto.BookingDetailsDto;
import com.FilmiPass.Service.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/filmipass/bookings")
@CrossOrigin("*")
public class BookingController {
    @Autowired
    private BookingDetailsService bookingDetailsService;

    @PostMapping("/create")
    public ResponseEntity<BookingDetailsDto> createBooking(@RequestBody BookingDetailsDto bookingDetailsDto){
        BookingDetailsDto createBooking = bookingDetailsService.createBooking(bookingDetailsDto);
        return  ResponseEntity.ok(createBooking);
    }

    @GetMapping("/filled-seats/{movieName}/{theaterName}/{city}/{showDate}/{showTime}")
    public List<String> getFIlledSeats(@PathVariable String movieName,
                                       @PathVariable String theaterName,
                                       @PathVariable String city,
                                       @PathVariable String showDate,
                                       @PathVariable String showTime
    ){
        return bookingDetailsService.getFilledSeats(movieName,theaterName,city, showDate,showTime);
    }
}
