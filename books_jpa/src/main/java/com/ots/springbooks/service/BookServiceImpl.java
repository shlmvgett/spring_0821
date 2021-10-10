package com.ots.springbooks.service;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.models.Book;
import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.interfaces.AuthorRepository;
import com.ots.springbooks.repositories.interfaces.BookRepository;
import com.ots.springbooks.repositories.interfaces.CommentRepository;
import com.ots.springbooks.repositories.interfaces.GenreRepository;
import com.ots.springbooks.service.interfaces.BookService;
import com.ots.springbooks.service.interfaces.IOService;
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
  private final CommentRepository commentRepository;

  @Override
  @Transactional
  public void getAllBooks() {
    List<Book> books = bookRepository.findAll();
    if (!CollectionUtils.isEmpty(books)) {
      books.forEach(book -> ioService.print(book.toString()));
    } else {
      ioService.print("books table is empty.");
    }
  }

  @Override
  @Transactional
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
        bookRepository.updateTitleById(bookId, bookName);
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
