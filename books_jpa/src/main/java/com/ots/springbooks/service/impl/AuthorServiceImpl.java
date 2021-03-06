package com.ots.springbooks.service.impl;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.repositories.AuthorRepository;
import com.ots.springbooks.service.AuthorService;
import com.ots.springbooks.service.IOService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Log4j2
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final IOService ioService;
  private final AuthorRepository authorRepository;

  @Override
  public void getAllAuthors() {
    List<Author> authors = authorRepository.findAll();
    if (!CollectionUtils.isEmpty(authors)) {
      authors.forEach(author -> ioService.print(author.toString()));
    } else {
      ioService.print("Authors table is empty.");
    }
  }

  @Override
  public void getAuthorById() {
    ioService.print("Type Author id:");
    long authorId = Long.parseLong(ioService.read());
    Optional<Author> author = authorRepository.findById(authorId);
    if (author.isPresent()) {
      ioService.print(author.toString());
    } else {
      ioService.print("Author wasn't found");
    }
  }

  @Override
  @Transactional
  public void insertAuthor() {
    ioService.print("Type Author name:");
    String authorName = ioService.read();
    authorRepository.save(new Author(authorName));
    ioService.print("Author was added");
  }

  @Override
  @Transactional
  public void updateAuthor() {
    ioService.print("Type Author id:");
    long authorId = Long.parseLong(ioService.read());
    Optional<Author> author = authorRepository.findById(authorId);
    if (author.isEmpty()) {
      ioService.print("Author with Id:" + authorId + " wasn't found");
    } else {
      ioService.print("Type Author name:");
      String authorName = ioService.read();
      author.get().setName(authorName);
      authorRepository.save(author.get());
      ioService.print("Author was updated");
    }
  }

  @Override
  public void deleteAuthor() {
    ioService.print("Type Author id:");
    long authorId = Long.parseLong(ioService.read());
    Optional<Author> author = authorRepository.findById(authorId);
    if (author.isPresent()) {
      authorRepository.deleteById(author.get());
      ioService.print("Author was deleted");
    } else {
      ioService.print("Author wasn't found");
    }
  }
}
