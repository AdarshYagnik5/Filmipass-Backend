package com.FilmiPass.Service;

import com.FilmiPass.Dto.BookingDetailsDto;
import com.FilmiPass.Modal.BookingDetails;
import com.FilmiPass.Repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookingDetailsService {

    @Autowired
    private BookingRepo bookingRepo;

    public BookingDetailsDto createBooking(BookingDetailsDto bookingDetailsDto) {
        BookingDetails bookingDetails = new BookingDetails();

        // Convert the comma-separated string to a List<String>
        List<String> seatNumbers = Arrays.asList(bookingDetailsDto.getSeatNumbers().split(","));

        bookingDetails.setMovieName(bookingDetailsDto.getMovieName());
        bookingDetails.setBookingTime(bookingDetailsDto.getBookingTime());
        bookingDetails.setSeatNumbers(seatNumbers);  // Set the List<String>
        bookingDetails.setNumberOfSeats(bookingDetailsDto.getNumberOfSeats());
        bookingDetails.setTheaterName(bookingDetailsDto.getTheaterName());
        bookingDetails.setCity(bookingDetailsDto.getCity());
        bookingDetails.setTotalPrice(bookingDetailsDto.getTotalPrice());
        bookingDetails.setShowDate(bookingDetailsDto.getShowDate());
        bookingDetails.setShowTime(bookingDetailsDto.getShowTime());

        BookingDetails savedBooking = bookingRepo.save(bookingDetails);

        BookingDetailsDto responseDto = new BookingDetailsDto();
        responseDto.setMovieName(savedBooking.getMovieName());
        responseDto.setTheaterName(savedBooking.getTheaterName());
        responseDto.setCity(savedBooking.getCity());
        responseDto.setNumberOfSeats(savedBooking.getNumberOfSeats());
        responseDto.setTotalPrice(savedBooking.getTotalPrice());
        responseDto.setBookingTime(savedBooking.getBookingTime());
        responseDto.setShowDate(savedBooking.getShowDate());
        responseDto.setShowTime(savedBooking.getShowTime());
        responseDto.setSeatNumbers(String.join(",", savedBooking.getSeatNumbers()));  // Convert List<String> back to comma-separated string for response

        return responseDto;
    }

    public List<String> getFilledSeats(String movieName, String theaterName, String city, String showDate, String showTime) {
        List<String> seatNumbersList = bookingRepo.findSeatNumbersByMovieAndTheaterAndCityAndShow(movieName, theaterName, city, showDate, showTime);

        // Flatten the list of seat numbers into a single string
        List<String> allSeats = new ArrayList<>();
        for (String seatNumbers : seatNumbersList) {
            allSeats.addAll(Arrays.asList(seatNumbers.replaceAll("[{}]", "").split(",")));
        }
        return allSeats;
    }
}
