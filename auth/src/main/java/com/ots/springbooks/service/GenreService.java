package com.ots.springbooks.service;

import com.ots.springbooks.models.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreService {

  List<Genre> getAllGenres();

  Optional<Genre> getGenreById(Long bookId);
}
