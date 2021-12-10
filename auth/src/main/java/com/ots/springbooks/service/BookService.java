package com.ots.springbooks.service;

import com.ots.springbooks.models.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

  List<Book> getAllBooks();

  Optional<Book> getBookById(Long bookId);

  Optional<Book> getBookByTitle(String bookName);

  Book insertBook(Book book);

  Book updateBook(Book book);

  void deleteBook(Long bookId);
}
