package com.FilmiPass.Repository;

import com.FilmiPass.Modal.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepo extends JpaRepository<Showtime,Long> {
    List<Showtime> findByTheater_TheaterId(Long theaterId);

    @Query("SELECT s FROM Showtime s WHERE s.theater.theaterId = :theaterId")
    List<Showtime> findByTheaterId(@Param("theaterId") Long theaterId);
}
