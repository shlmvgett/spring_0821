package com.ots.springbooks.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
//import com.mongodb.client.MongoDatabase;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.ots.springbooks.models.Author;
import com.ots.springbooks.models.Book;
import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.AuthorRepository;
import com.ots.springbooks.repositories.BookRepository;
import com.ots.springbooks.repositories.GenreRepository;

import java.util.UUID;

@ChangeLog(order = "001")
public class DatabaseChangelog {

  private Author author1;
  private Author author2;

  private Genre genre1;
  private Genre genre2;

  @ChangeSet(order = "001", id = "insertAuthors", author = "dev")
  public void insertAuthors(AuthorRepository authorRepository) {
    author1 = authorRepository.save(new Author("author_1")).block();
    author2 = authorRepository.save(new Author("author_2")).block();
  }

  @ChangeSet(order = "002", id = "insertGenres", author = "dev")
  public void insertGenres(GenreRepository genreRepository) {
    genre1 = genreRepository.save(new Genre("genre_1")).block();
    genre2 = genreRepository.save(new Genre("genre_2")).block();
  }

  @ChangeSet(order = "003", id = "insertBooks", author = "dev")
  public void insertBooks(BookRepository bookRepository) {
    Book book1 = new Book("book_1", author1, genre1);
    Book book2 = new Book("book_2", author2, genre2);
    Book book3 = new Book("book_3", author1, genre2);

    bookRepository.save(book1).block();
    bookRepository.save(book2).block();
    bookRepository.save(book3).block();
  }
}
