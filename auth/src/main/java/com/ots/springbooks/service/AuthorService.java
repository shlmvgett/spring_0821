package com.ots.springbooks.service;

import com.ots.springbooks.models.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

  List<Author> getAllAuthors();

  Optional<Author> getAuthorById(Long authorId);
}
