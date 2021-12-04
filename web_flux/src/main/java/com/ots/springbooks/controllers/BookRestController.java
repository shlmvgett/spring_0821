package com.ots.springbooks.controllers;

import com.ots.springbooks.controllers.dto.BookCreationReq;
import com.ots.springbooks.models.Book;
import com.ots.springbooks.repositories.AuthorRepository;
import com.ots.springbooks.repositories.BookRepository;
import com.ots.springbooks.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@AllArgsConstructor
public class BookRestController {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final GenreRepository genreRepository;

  @GetMapping("/api/books")
  public Flux<Book> getBooks() {
    return bookRepository.findAll();
  }

  @PostMapping("/api/book")
  public Mono<Book> saveClient(@RequestBody BookCreationReq bookCreationReq) {
    return Mono.zip(
            authorRepository.findById(bookCreationReq.getAuthorId()),
            genreRepository.findById(bookCreationReq.getGenreId()))
        .zipWhen(
            data -> {
              Book book = new Book(bookCreationReq.getTitle(), data.getT1(), data.getT2());
              return bookRepository.save(book);
            })
        .then(Mono.empty());
  }
}
