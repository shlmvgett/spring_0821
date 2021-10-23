package com.ots.springbooks.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.ots.springbooks.models.Author;
import com.ots.springbooks.models.Book;
import com.ots.springbooks.models.Comment;
import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.AuthorRepository;
import com.ots.springbooks.repositories.BookRepository;
import com.ots.springbooks.repositories.GenreRepository;
import java.util.List;
import java.util.UUID;

@ChangeLog
public class DatabaseChangelog {

  private final Author author1 = new Author(UUID.randomUUID(), "author_1");
  private final Author author2 = new Author(UUID.randomUUID(), "author_2");

  private final Genre genre1 = new Genre(UUID.randomUUID(), "genre_1");
  private final Genre genre2 = new Genre(UUID.randomUUID(), "genre_2");

  private final Book book1 =
      new Book(
          UUID.randomUUID(),
          "book_1",
          author1,
          genre1,
          List.of(new Comment("comment1_1"), new Comment("comment1_2")));
  private final Book book2 =
      new Book(UUID.randomUUID(), "book_2", author2, genre2, List.of(new Comment("comment2_1")));
  private final Book book3 =
      new Book(UUID.randomUUID(), "book_3", author1, genre2, List.of(new Comment("comment3_1")));

  @ChangeSet(order = "001", id = "dropDb", author = "dev", runAlways = true)
  public void dropDb(MongoDatabase db) {
    db.drop();
  }

  @ChangeSet(order = "002", id = "insertAuthors", author = "dev")
  public void insertAuthors(AuthorRepository authorRepository) {
    authorRepository.save(author1);
    authorRepository.save(author2);
  }

  @ChangeSet(order = "003", id = "insertGenres", author = "dev")
  public void insertGenres(GenreRepository genreRepository) {
    genreRepository.save(genre1);
    genreRepository.save(genre2);
  }

  @ChangeSet(order = "004", id = "insertBooks", author = "dev")
  public void insertBooks(BookRepository bookRepository) {
    bookRepository.save(book1);
    bookRepository.save(book2);
    bookRepository.save(book3);
  }
}
