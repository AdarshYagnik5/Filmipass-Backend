package com.FilmiPass.Repository;

import com.FilmiPass.Modal.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<BookingDetails, Long> {

    @Query("SELECT b.seatNumbers FROM BookingDetails b " +
            "WHERE b.movieName = :movieName AND b.theaterName = :theaterName " +
            "AND b.city = :city AND b.showDate = :showDate AND b.showTime = :showTime")
    List<String> findSeatNumbersByMovieAndTheaterAndCityAndShow(String movieName, String theaterName, String city, String showDate, String showTime);


}
