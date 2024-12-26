package com.FilmiPass.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
public class ShowtimeDto {
    private Long showtimeId;
    private Long theaterId;
    private LocalDate showDate;
    private LocalTime showTime;
}
