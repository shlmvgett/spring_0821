package com.ots.springbooks.controllers;

import com.ots.springbooks.models.Genre;
import com.ots.springbooks.service.GenreService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestGenreController {

  private final GenreService genreService;

  public RestGenreController(GenreService genreService) {
    this.genreService = genreService;
  }

  @GetMapping("/api/genres")
  public List<Genre> getGenres() {
    return genreService.getAllGenres();
  }
}
