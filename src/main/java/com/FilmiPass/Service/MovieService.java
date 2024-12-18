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
    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }

    public Optional<Movie> getMovieById(Long movieId) {
        return movieRepo.findById(movieId);
    }

    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public Movie updateMovie(Long movieId, Movie movieDetails) {
        Optional<Movie> movieOptional = movieRepo.findById(movieId);

        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            movie.setTitle(movieDetails.getTitle());
            movie.setGenre(movieDetails.getGenre());
            movie.setLanguage(movieDetails.getLanguage());
            movie.setDuration(movieDetails.getDuration());
            movie.setReleaseDate(movieDetails.getReleaseDate());
            movie.setDescription(movieDetails.getDescription());
            return movieRepo.save(movie);
        } else {
            throw new RuntimeException("Movie not found with id: " + movieId);
        }
    }

    public void deleteMovie(Long movieId){
        try{
            movieRepo.deleteById(movieId);
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Error in deleting the mvie" + e);
        }
    }
}



