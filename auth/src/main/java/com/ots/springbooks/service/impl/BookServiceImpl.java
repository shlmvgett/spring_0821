package com.ots.springbooks.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ots.springbooks.models.Author;
import com.ots.springbooks.models.Book;
import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.AuthorRepository;
import com.ots.springbooks.repositories.BookRepository;
import com.ots.springbooks.repositories.GenreRepository;
import com.ots.springbooks.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final GenreRepository genreRepository;

  @Override
  @Transactional(readOnly = true)
  @PostFilter("hasRole('ADMIN') or filterObject.owner == authentication.name")
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  // For Hystrix debug
  @Override
  @HystrixCommand(fallbackMethod = "getAllBooksFallback")
  @Transactional(readOnly = true)
  public List<Book> getAllBooksWithException() {
    if (true) throw new RuntimeException();
    return bookRepository.findAll();
  }

  public List<Book> getAllBooksFallback() {
    log.warn("Exception fallback: getAllBooksFallback");
    return new ArrayList<>(List.of(bookRepository.findById(1L).get()));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Book> getBookById(Long bookId) {
    return bookRepository.findById(bookId);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Book> getBookByTitle(String bookName) {
    return bookRepository.findByTitle(bookName);
  }

  @Override
  @Transactional
  public Book insertBook(Book book) {
    Optional<Author> author = authorRepository.findById(book.getAuthor().getId());
    Optional<Genre> genre = genreRepository.findById(book.getGenre().getId());

    if (author.isPresent() && genre.isPresent()) {
      Book newBook = new Book(book.getTitle(), book.getOwner(), author.get(), genre.get());
      return bookRepository.save(newBook);
    } else {
      return null;
    }
  }

  @Override
  @Transactional
  public Book updateBook(Book book) {
    Optional<Book> bookDb = bookRepository.findById(book.getId());
    if (bookDb.isPresent()) {
      long authorId = book.getAuthor().getId();
      long genreId = book.getGenre().getId();

      Optional<Author> author = authorRepository.findById(authorId);
      Optional<Genre> genre = genreRepository.findById(genreId);

      if (author.isPresent() && genre.isPresent()) {

        bookDb.get().setTitle(book.getTitle());
        return bookRepository.save(bookDb.get());
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  @Override
  @Transactional
  public void deleteBook(Long bookId) {
    Optional<Book> book = bookRepository.findById(bookId);
  }
}
