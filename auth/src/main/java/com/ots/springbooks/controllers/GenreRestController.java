package com.ots.springbooks.controllers;

import com.ots.springbooks.models.Genre;
import com.ots.springbooks.service.GenreService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GenreRestController {

  private final GenreService genreService;

  @GetMapping("/api/genres")
  public List<Genre> getGenres() {
    return genreService.getAllGenres();
  }
}
