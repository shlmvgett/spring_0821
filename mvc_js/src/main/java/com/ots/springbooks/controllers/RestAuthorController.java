package com.ots.springbooks.controllers;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.service.AuthorService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthorController {

  private final AuthorService authorService;

  public RestAuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/api/authors")
  public List<Author> getAuthors() {
    return authorService.getAllAuthors();
  }
}
