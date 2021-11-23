package com.ots.springbooks.service.impl;

import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.GenreRepository;
import com.ots.springbooks.service.GenreService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreRepository genreRepository;

  @Override
  public List<Genre> getAllGenres() {
    return genreRepository.findAll();
  }

  @Override
  public Optional<Genre> getGenreById(Long genreId) {
    return genreRepository.findById(genreId);
  }
}
