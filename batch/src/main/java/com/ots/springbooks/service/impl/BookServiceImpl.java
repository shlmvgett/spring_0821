package com.ots.springbooks.service.impl;

import com.ots.springbooks.models.jpa.BookJpa;
import com.ots.springbooks.models.mongo.BookMongo;
import com.ots.springbooks.repositories.jpa.BookRepository;
import com.ots.springbooks.repositories.mongo.BookMongoRepository;
import com.ots.springbooks.service.BookService;
import com.ots.springbooks.service.IOService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Log4j2
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final IOService ioService;
  private final BookRepository bookRepository;
  private final BookMongoRepository bookMongoRepository;

  @Override
  @Transactional(readOnly = true)
  public void getAllBooksJpa() {
    List<BookJpa> books = bookRepository.findAll();
    if (!CollectionUtils.isEmpty(books)) {
      books.forEach(book -> ioService.print(book.toString()));
    } else {
      ioService.print("books table is empty.");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public void getAllBooksMongo() {
    List<BookMongo> books = bookMongoRepository.findAll();
    if (!CollectionUtils.isEmpty(books)) {
      books.forEach(book -> ioService.print(book.toString()));
    } else {
      ioService.print("books table is empty.");
    }
  }
}
