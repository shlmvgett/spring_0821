package com.ots.springbooks.controllers;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.service.AuthorService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthorRestController {

  private final AuthorService authorService;

  @GetMapping("/api/authors")
  public List<Author> getAuthors() {
    return authorService.getAllAuthors();
  }
}
