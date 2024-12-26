package com.FilmiPass.Controller;

import com.FilmiPass.Dto.ShowtimeDto;
import com.FilmiPass.Modal.Showtime;
import com.FilmiPass.Modal.Theater;
import com.FilmiPass.Service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmipass/showtime")
@CrossOrigin("*")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<ShowtimeDto>> getShowtimesByTheaterId(@PathVariable Long theaterId) {
        List<ShowtimeDto> showtimes = showtimeService.getShowtimesByTheaterId(theaterId);
        if (showtimes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(showtimes);
        }
        return ResponseEntity.ok(showtimes);
    }

    @PostMapping
    public ResponseEntity<ShowtimeDto> createShowtime(@RequestBody ShowtimeDto request) {
        try {
            ShowtimeDto createdShowtime = showtimeService.addShowtime(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdShowtime);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
