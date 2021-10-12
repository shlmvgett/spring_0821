package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreRepository {

  List<Genre> findAll();

  Optional<Genre> findById(long id);

  Genre save(Genre genre);

  void deleteById(Genre genre);
}
