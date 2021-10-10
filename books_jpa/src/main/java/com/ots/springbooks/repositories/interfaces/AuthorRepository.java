package com.ots.springbooks.repositories.interfaces;

import com.ots.springbooks.models.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

  List<Author> findAll();

  Optional<Author> findById(long id);

  Author save(Author author);

  void updateNameById(long id, String name);

  void deleteById(long id);
}
