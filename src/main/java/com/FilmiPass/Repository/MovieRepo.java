package com.FilmiPass.Repository;

import com.FilmiPass.Modal.Movie;
import com.FilmiPass.Modal.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {
    boolean existsByTitle(String title);

    boolean existsByLocation(String location);

    List<Movie> findByLocation(String location);

}
