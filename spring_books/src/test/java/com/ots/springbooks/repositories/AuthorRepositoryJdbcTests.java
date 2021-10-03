package com.ots.springbooks.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ots.springbooks.domain.Author;
import com.ots.springbooks.repositories.interfaces.AuthorRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@JdbcTest
@ExtendWith(SpringExtension.class)
@Import(AuthorRepositoryJdbc.class)
public class AuthorRepositoryJdbcTests {

  @Autowired private AuthorRepository authorRepository;

  @Test
  @DirtiesContext
  void getAllAuthorsTest() {
    List<Author> authors = authorRepository.getAll();
    assertEquals(2, authors.size());
  }

  @Test
  @DirtiesContext
  void getAuthorByIdTest() {
    Author author = authorRepository.getById(1);
    assertEquals("author_1", author.getName());
  }

  @Test
  @DirtiesContext
  void insertAuthorTest() {
    List<Author> authors = authorRepository.getAll();
    assertEquals(2, authors.size());

    authorRepository.insert(new Author("test_name"));

    authors = authorRepository.getAll();
    assertEquals(3, authors.size());
  }

  @Test
  @DirtiesContext
  void updateAuthorTest() {
    final String NEW_NAME = "new_name";
    Author author = authorRepository.getById(1);
    assertEquals("author_1", author.getName());

    authorRepository.update(new Author(author.getId(), NEW_NAME));

    author = authorRepository.getById(1);
    assertEquals(NEW_NAME, author.getName());
  }

  @Test
  @DirtiesContext
  void deleteAuthorTest() {
    List<Author> authors = authorRepository.getAll();
    assertEquals(2, authors.size());

    authorRepository.delete(authors.get(0));

    authors = authorRepository.getAll();
    assertEquals(1, authors.size());
  }
}
