package com.ots.springbooks.repositories;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.ots.springbooks.models.Author;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@Log4j2
@ChangeLog
@SpringBootTest
public class AuthorRepositoryJpaTests {

  private final Author author1 = new Author("1", "author_t_1");
  private final Author author2 = new Author("2", "author_t_2");

  @Autowired AuthorRepository authorRepository;

  @ChangeSet(order = "1001", id = "insertAuthors", author = "test")
  public void insertAuthors(AuthorRepository repository) {
    repository.save(author1);
    repository.save(author2);
  }

  @Test
  @DirtiesContext
  void getAllAuthorsTest() {
    List<Author> authors = authorRepository.findAll();
    assertEquals(2, authors.size());
  }

  @Test
  @DirtiesContext
  void getAuthorByIdTest() {
    Optional<Author> author = authorRepository.findById("1");
    assertEquals("author_t_1", author.get().getName());
  }

  @Test
  @DirtiesContext
  void insertAuthorTest() {
    authorRepository.save(new Author("test_name"));
    Author author = authorRepository.findByName("test_name").get();
    assertThat(author).isNotNull();
  }

  @Test
  @DirtiesContext
  void updateAuthorTest() {
    final String NEW_NAME = "new_name";
    Author author = authorRepository.findById("1").get();
    assertEquals("author_t_1", author.getName());

    author.setName(NEW_NAME);
    authorRepository.save(author);

    author = authorRepository.findById("1").get();
    assertEquals(NEW_NAME, author.getName());
  }

  @Test
  @DirtiesContext
  void deleteAuthorTest() {
    Optional<Author> author = authorRepository.findById("2");
    assertThat(author).isNotNull();

    authorRepository.deleteById(author.get().getId());

    author = authorRepository.findById("2");
    assertThat(author.isPresent()).isFalse();
  }
}
