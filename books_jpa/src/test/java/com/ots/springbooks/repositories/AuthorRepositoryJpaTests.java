package com.ots.springbooks.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.repositories.impl.AuthorRepositoryJpa;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(AuthorRepositoryJpa.class)
public class AuthorRepositoryJpaTests {

  @Autowired private AuthorRepository authorRepository;
  @Autowired private TestEntityManager em;

  @Test
  @DirtiesContext
  void getAllAuthorsTest() {
    List<Author> authors = authorRepository.findAll();
    assertEquals(2, authors.size());
  }

  @Test
  @DirtiesContext
  void getAuthorByIdTest() {
    Optional<Author> author = authorRepository.findById(1);
    assertEquals("author_1", author.get().getName());
  }

  @Test
  @DirtiesContext
  void insertAuthorTest() {
    List<Author> authors = authorRepository.findAll();
    assertEquals(2, authors.size());

    authorRepository.save(new Author("test_name"));

    authors = authorRepository.findAll();
    assertEquals(3, authors.size());
  }

  @Test
  @DirtiesContext
  void updateAuthorTest() {
    final String NEW_NAME = "new_name";
    Author author = em.find(Author.class, 1L);
    assertEquals("author_1", author.getName());

    author.setName(NEW_NAME);
    authorRepository.save(author);

    author = em.find(Author.class, 1L);
    assertEquals(NEW_NAME, author.getName());
  }

  @Test
  @DirtiesContext
  void deleteAuthorTest() {
    Author author = em.find(Author.class, 3L);
    assertThat(author).isNotNull();

    authorRepository.deleteById(author);
    author = em.find(Author.class, 3L);
    assertThat(author).isNull();
  }
}
