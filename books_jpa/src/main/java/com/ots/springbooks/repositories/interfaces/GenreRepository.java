package com.ots.springbooks.repositories.interfaces;

import com.ots.springbooks.models.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreRepository {

  List<Genre> findAll();

  Optional<Genre> findById(long id);

  Genre save(Genre genre);

  void updateTitleById(long id, String title);

  void deleteById(long id);
}
