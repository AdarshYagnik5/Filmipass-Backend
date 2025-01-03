package com.FilmiPass.Modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private String genre;
    private String language;
    private int duration;
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String theaterId;

    @Column(nullable = false)
    private String location;

    @Column(length = 500)
    private String description;
}
