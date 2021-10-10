package com.ots.springbooks.repositories.interfaces;

import com.ots.springbooks.models.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

  List<Book> findAll();

  Optional<Book> findById(long id);

  List<Book> findByTitle(String name);

  Book save(Book book);

  void updateTitleById(long id, String title);

  void deleteById(long id);
}
