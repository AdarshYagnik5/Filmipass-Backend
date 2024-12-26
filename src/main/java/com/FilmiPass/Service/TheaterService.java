package com.FilmiPass.Service;

import com.FilmiPass.Modal.Theater;
import com.FilmiPass.Repository.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepo theaterRepo;


    public List<Theater> getAllTheaters() {
        try{
            return theaterRepo.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public List<Theater> getTheaterByLocation(String location) {

        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        try {
            List<Theater> theatersByLocation = theaterRepo.findByLocation(location);
            return theatersByLocation.isEmpty() ? Collections.emptyList() : theatersByLocation;
        } catch (Exception e) {
            // Log the exception for debugging
            System.err.println("Error fetching theaters by location: " + e.getMessage());
            throw new RuntimeException("Error fetching theaters by location", e);
        }
    }


    public Theater addTheater(Theater theater) {
        try {
            Optional<Theater> existingTheater = theaterRepo.findByTheaterNameAndLocation(theater.getTheaterName(), theater.getLocation());
            if (existingTheater.isPresent()) {
                throw new IllegalArgumentException("Theater with the same name and location already exists.");
            }
            return theaterRepo.save(theater);
        }  catch (Exception e) {
            throw new RuntimeException("An error occurred while adding the theater: " + e.getMessage());
        }
    }

    public void deleteTheater(Long id) {
        try {
            if (!theaterRepo.existsById(id)) {
                throw new RuntimeException("Theater not found for deletion");
            }

            theaterRepo.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("An error occurred while deleting the theater: " + ex.getMessage());
        }
    }

}
