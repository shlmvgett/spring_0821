package com.ots.springbooks.service.impl;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.repositories.AuthorRepository;
import com.ots.springbooks.service.AuthorService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  @Override
  public Optional<Author> getAuthorById(Long authorId) {
    return authorRepository.findById(authorId);
  }
}
