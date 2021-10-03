package com.ots.springbooks.service;

import com.ots.springbooks.domain.Author;
import com.ots.springbooks.repositories.interfaces.AuthorRepository;
import com.ots.springbooks.service.interfaces.AuthorService;
import com.ots.springbooks.service.interfaces.IOService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Log4j2
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final IOService ioService;
  private final AuthorRepository authorRepository;

  @Override
  public void getAllAuthors() {
    List<Author> authors = authorRepository.getAll();
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
    Author author = authorRepository.getById(authorId);
    if (author != null) {
      ioService.print(author.toString());
    } else {
      ioService.print("Author wasn't found");
    }
  }

  @Override
  public void insertAuthor() {
    ioService.print("Type Author name:");
    String authorName = ioService.read();
    authorRepository.insert(new Author(authorName));
    ioService.print("Author was added");
  }

  @Override
  public void updateAuthor() {
    ioService.print("Type Author id:");
    long authorId = Long.parseLong(ioService.read());
    Author author = authorRepository.getById(authorId);
    if (author == null) {
      ioService.print("Author with Id:" + authorId + " wasn't found");
    } else {
      ioService.print("Type Author name:");
      String authorName = ioService.read();
      authorRepository.update(new Author(author.getId(), authorName));
      ioService.print("Author was updated");
    }
  }

  @Override
  public void deleteAuthor() {
    ioService.print("Type Author id:");
    long authorId = Long.parseLong(ioService.read());
    Author author = authorRepository.getById(authorId);
    if (null != author) {
      authorRepository.delete(author);
      ioService.print("Author was deleted");
    } else {
      ioService.print("Author wasn't found");
    }
  }
}
