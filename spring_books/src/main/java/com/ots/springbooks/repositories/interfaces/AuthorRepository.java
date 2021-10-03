package com.ots.springbooks.repositories.interfaces;

import com.ots.springbooks.domain.Author;
import java.util.List;

public interface AuthorRepository {

  List<Author> getAll();

  Author getById(long id);

  void insert(Author author);

  void update(Author author);

  void delete(Author author);
}
