package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

  List<Author> findAll();

  Optional<Author> findById(long id);

  Author save(Author author);

  void deleteById(Author author);
}
