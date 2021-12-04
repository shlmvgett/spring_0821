package com.ots.springbooks.controllers;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class AuthorRestController {

  private final AuthorRepository authorRepository;

  @GetMapping("/api/authors")
  public Flux<Author> getAuthors() {
    return authorRepository.findAll();
  }
}
