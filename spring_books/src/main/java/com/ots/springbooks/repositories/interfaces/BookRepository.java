package com.ots.springbooks.repositories.interfaces;

import com.ots.springbooks.domain.Book;
import java.util.List;

public interface BookRepository {

  List<Book> getAll();

  Book getById(long id);

  void insert(Book book);

  void update(Book book);

  void delete(Book book);
}
