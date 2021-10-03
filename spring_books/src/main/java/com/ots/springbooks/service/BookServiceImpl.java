package com.ots.springbooks.service;

import com.ots.springbooks.domain.Author;
import com.ots.springbooks.domain.Book;
import com.ots.springbooks.domain.Genre;
import com.ots.springbooks.repositories.interfaces.AuthorRepository;
import com.ots.springbooks.repositories.interfaces.BookRepository;
import com.ots.springbooks.repositories.interfaces.GenreRepository;
import com.ots.springbooks.service.interfaces.BookService;
import com.ots.springbooks.service.interfaces.IOService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
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
  public void getAllBooks() {
    List<Book> books = bookRepository.getAll();
    if (!CollectionUtils.isEmpty(books)) {
      books.forEach(book -> ioService.print(book.toString()));
    } else {
      ioService.print("books table is empty.");
    }
  }

  @Override
  public void getBookById() {
    ioService.print("Type book id:");
    long bookId = Long.parseLong(ioService.read());
    Book book = bookRepository.getById(bookId);
    if (book != null) {
      ioService.print(book.toString());
    } else {
      ioService.print("book wasn't found");
    }
  }

  @Override
  public void insertBook() {
    ioService.print("Type book name:");
    String bookName = ioService.read();

    ioService.print("Type author id:");
    long authorId = Long.parseLong(ioService.read());

    ioService.print("Type genre id:");
    long genreId = Long.parseLong(ioService.read());

    Author author = authorRepository.getById(authorId);
    Genre genre = genreRepository.getById(genreId);

    if (author != null && genre != null) {
      bookRepository.insert(new Book(bookName, author, genre));
      ioService.print("Book was added");
    } else {
      ioService.print("Incorrect authorId or genreId");
    }
  }

  @Override
  public void updateBook() {
    ioService.print("Type book id for updating:");
    long bookId = Long.parseLong(ioService.read());

    Book book = bookRepository.getById(bookId);
    if (book != null) {
      ioService.print(book.toString());

      ioService.print("Type new book name:");
      String bookName = ioService.read();

      ioService.print("Type author id:");
      long authorId = Long.parseLong(ioService.read());

      ioService.print("Type genre id:");
      long genreId = Long.parseLong(ioService.read());

      Author author = authorRepository.getById(authorId);
      Genre genre = genreRepository.getById(genreId);

      if (author != null && genre != null) {
        bookRepository.update(new Book(bookId, bookName, author, genre));
        ioService.print("Book was updated");
      } else {
        ioService.print("Incorrect authorId or genreId");
      }
    } else {
      ioService.print("book wasn't found");
    }
  }

  @Override
  public void deleteBook() {
    ioService.print("Type book id:");
    long bookId = Long.parseLong(ioService.read());
    Book book = bookRepository.getById(bookId);
    if (null != book) {
      bookRepository.delete(book);
      ioService.print("book was deleted");
    } else {
      ioService.print("book wasn't found");
    }
  }
}
