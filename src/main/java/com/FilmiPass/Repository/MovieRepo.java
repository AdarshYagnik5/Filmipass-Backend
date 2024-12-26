package com.FilmiPass.Repository;

import com.FilmiPass.Modal.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {
    boolean existsByTitle(String title);
}
