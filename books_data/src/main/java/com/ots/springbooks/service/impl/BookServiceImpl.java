package com.ots.springbooks.service.impl;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.models.Book;
import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.AuthorRepository;
import com.ots.springbooks.repositories.BookRepository;
import com.ots.springbooks.repositories.GenreRepository;
import com.ots.springbooks.service.BookService;
import com.ots.springbooks.service.IOService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Log4j2
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final IOService ioService;
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final GenreRepository genreRepository;

  @Override
  @Transactional(readOnly = true)
  public void getAllBooks() {
    List<Book> books = bookRepository.findAll();
    if (!CollectionUtils.isEmpty(books)) {
      books.forEach(book -> ioService.print(book.toString()));
    } else {
      ioService.print("books table is empty.");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public void getBookById() {
    ioService.print("Type book id:");
    long bookId = Long.parseLong(ioService.read());
    Optional<Book> book = bookRepository.findById(bookId);
    if (book.isPresent()) {
      ioService.print(book.toString());
    } else {
      ioService.print("book wasn't found");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public void getBookByTitle() {
    ioService.print("Type book title:");
    String bookName = ioService.read();
    Optional<Book> book = bookRepository.findByTitle(bookName);
    if (book.isPresent()) {
      ioService.print(book.toString());
    } else {
      ioService.print("book wasn't found");
    }
  }

  @Override
  @Transactional
  public void insertBook() {
    ioService.print("Type book name:");
    String bookName = ioService.read();

    ioService.print("Type author id:");
    long authorId = Long.parseLong(ioService.read());

    ioService.print("Type genre id:");
    long genreId = Long.parseLong(ioService.read());

    Optional<Author> author = authorRepository.findById(authorId);
    Optional<Genre> genre = genreRepository.findById(genreId);

    if (author.isPresent() && genre.isPresent()) {
      bookRepository.save(new Book(bookName, author.get(), genre.get()));
      ioService.print("Book was added");
    } else {
      ioService.print("Incorrect authorId or genreId");
    }
  }

  @Override
  @Transactional
  public void updateBook() {
    ioService.print("Type book id for updating:");
    long bookId = Long.parseLong(ioService.read());

    Optional<Book> book = bookRepository.findById(bookId);
    if (book.isPresent()) {
      ioService.print(book.toString());

      ioService.print("Type new book name:");
      String bookName = ioService.read();

      ioService.print("Type author id:");
      long authorId = Long.parseLong(ioService.read());

      ioService.print("Type genre id:");
      long genreId = Long.parseLong(ioService.read());

      Optional<Author> author = authorRepository.findById(authorId);
      Optional<Genre> genre = genreRepository.findById(genreId);

      if (author.isPresent() && genre.isPresent()) {
        book.get().setTitle(bookName);
        bookRepository.save(book.get());
        ioService.print("Book was updated");
      } else {
        ioService.print("Incorrect authorId or genreId");
      }
    } else {
      ioService.print("book wasn't found");
    }
  }

  @Override
  @Transactional
  public void deleteBook() {
    ioService.print("Type book id:");
    long bookId = Long.parseLong(ioService.read());
    Optional<Book> book = bookRepository.findById(bookId);
    if (book.isPresent()) {
      bookRepository.deleteById(book.get().getId());
      ioService.print("book was deleted");
    } else {
      ioService.print("book wasn't found");
    }
  }
}
