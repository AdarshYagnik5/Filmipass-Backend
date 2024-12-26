package com.FilmiPass.Service;

import com.FilmiPass.Dto.ShowtimeDto;
import com.FilmiPass.Modal.Showtime;
import com.FilmiPass.Modal.Theater;
import com.FilmiPass.Repository.ShowtimeRepo;
import com.FilmiPass.Repository.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepo showtimeRepo;

    @Autowired
    private TheaterRepo theaterRepo;


    public List<ShowtimeDto> getShowtimesByTheaterId(Long theaterId) {
        // Fetch the showtimes for the given theaterId
        List<Showtime> showtimes = showtimeRepo.findByTheaterId(theaterId);

        // Map each Showtime entity to ShowtimeResponseDto manually
        return showtimes.stream()
                .map(showtime -> {
                    ShowtimeDto dto = new ShowtimeDto();
                    dto.setShowtimeId(showtime.getShowtimeId());
                    dto.setShowDate(showtime.getShowDate());
                    dto.setShowTime(showtime.getShowTime());

                    // Map the theaterId and optional theaterName
                    dto.setTheaterId(showtime.getTheater().getTheaterId());

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ShowtimeDto toResponseDto(Showtime showtime) {
        ShowtimeDto dto = new ShowtimeDto();
        dto.setShowtimeId(showtime.getShowtimeId());
        dto.setTheaterId(showtime.getTheater().getTheaterId());
        dto.setShowDate(showtime.getShowDate());
        dto.setShowTime(showtime.getShowTime());
        return dto;
    }
    public ShowtimeDto addShowtime(ShowtimeDto showtimeDto) {
        Theater theater = theaterRepo.findById(showtimeDto.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found"));
        Showtime showtime = new Showtime();
        showtime.setTheater(theater);
        showtime.setShowDate(showtimeDto.getShowDate());
        showtime.setShowTime(showtimeDto.getShowTime());
        Showtime savedShowtime = showtimeRepo.save(showtime);
        return toResponseDto(savedShowtime);
    }
}
