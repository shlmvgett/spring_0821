package com.ots.springbooks.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ots.springbooks.controllers.dto.BookCreationReq;
import com.ots.springbooks.models.Author;

import java.util.List;
import java.util.Optional;

import com.ots.springbooks.models.Book;
import com.ots.springbooks.models.Genre;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRestControllerTest {

  @Autowired
  private WebTestClient webClient;

  @Test
  void getBooksTest() {
    webClient.get()
        .uri("/api/books")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Book.class);
  }

  @Test
  void getGenresTest() {
    webClient.get()
        .uri("/api/genres")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Genre.class);
  }

  @Test
  void getAuthorsTest() {
    webClient.get()
        .uri("/api/authors")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Author.class);
  }

  @Test
  void addBookTest() {
    BookCreationReq newBookDto = new BookCreationReq("title", "authorId", "genreId");
    webClient.post()
        .uri("/api/book")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(newBookDto))
        .exchange()
        .expectStatus().isOk();
  }
}
