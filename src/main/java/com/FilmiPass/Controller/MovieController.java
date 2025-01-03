package com.FilmiPass.Controller;

import com.FilmiPass.Modal.Movie;
import com.FilmiPass.Modal.Theater;
import com.FilmiPass.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmipass/movies")
@CrossOrigin("*")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return  ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/loc/{location}")
    public ResponseEntity<List<Movie>> getAllMoviesByLoc(@PathVariable String location){
        return  ResponseEntity.ok(movieService.getMoviesByLocation(location));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        return movieService.getMovieById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return ResponseEntity.ok("Movie added successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted Successfully");
    }
}
