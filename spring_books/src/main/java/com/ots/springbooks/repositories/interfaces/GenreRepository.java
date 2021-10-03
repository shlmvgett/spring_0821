package com.ots.springbooks.repositories.interfaces;

import com.ots.springbooks.domain.Genre;
import java.util.List;

public interface GenreRepository {

  List<Genre> getAll();

  Genre getById(long id);

  void insert(Genre genre);

  void update(Genre genre);

  void delete(Genre genre);
}
