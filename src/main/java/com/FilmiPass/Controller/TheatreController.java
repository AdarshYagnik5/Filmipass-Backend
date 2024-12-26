package com.FilmiPass.Controller;

import com.FilmiPass.Modal.Theater;
import com.FilmiPass.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmipass/theater")
@CrossOrigin("*")
public class TheatreController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<Theater> addTheater(@RequestBody Theater theater){
        return ResponseEntity.ok(theaterService.addTheater(theater));
    }

    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters(){
        return  ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @GetMapping("/{location}")
    public ResponseEntity<List<Theater>> getAllTheatersByLoc(@PathVariable String location){
        return  ResponseEntity.ok(theaterService.getTheaterByLocation(location));
    }


    @DeleteMapping
    public ResponseEntity<String> deleteTheater(@PathVariable Long id){
        theaterService.deleteTheater(id);
        return ResponseEntity.ok("Theater deleted successfully");
    }
}
