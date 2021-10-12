package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

  List<Book> findAll();

  Optional<Book> findById(long id);

  List<Book> findByTitle(String name);

  Book save(Book book);

  void deleteById(Book book);
}
