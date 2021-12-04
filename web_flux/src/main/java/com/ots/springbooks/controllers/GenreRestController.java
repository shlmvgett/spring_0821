package com.ots.springbooks.controllers;

import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class GenreRestController {

  private final GenreRepository genreRepository;

  @GetMapping("/api/genres")
  public Flux<Genre> getGenres() {
    return genreRepository.findAll();
  }
}
