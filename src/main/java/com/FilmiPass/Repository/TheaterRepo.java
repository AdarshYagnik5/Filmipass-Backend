package com.FilmiPass.Repository;

import com.FilmiPass.Modal.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRepo extends JpaRepository<Theater, Long> {
    Optional<Theater> findByTheaterNameAndLocation(String theaterName, String location);


    List<Theater> findByLocation(String location);
}
