package com.FilmiPass.Service;

import com.FilmiPass.Modal.Movie;
import com.FilmiPass.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    public List<Movie> getAllMovies() {
        try {
            return movieRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Movie> getMovieById(Long movieId) {
        try {
            return movieRepo.findById(movieId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Movie addMovie(Movie movie) {
        try {
            if (movieRepo.existsByTitle(movie.getTitle())) {  // Corrected here
                throw new IllegalArgumentException("Movie already exists");
            }
            return movieRepo.save(movie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Movie updateMovie(Long movieId, Movie movieDetails) {
        try {
            Optional<Movie> movieOptional = movieRepo.findById(movieId);

            if (movieOptional.isEmpty()) {
                throw new IllegalArgumentException("Movie not found with id");
            }
            Movie movie = movieOptional.get();
            movie.setTitle(movieDetails.getTitle());
            movie.setGenre(movieDetails.getGenre());
            movie.setLanguage(movieDetails.getLanguage());
            movie.setDuration(movieDetails.getDuration());
            movie.setReleaseDate(movieDetails.getReleaseDate());
            movie.setDescription(movieDetails.getDescription());

            return movieRepo.save(movie);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteMovie(Long movieId) {
        try {
            movieRepo.deleteById(movieId);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Error in deleting the movie" + e);
        }
    }
}
