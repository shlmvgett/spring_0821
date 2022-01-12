package com.ots.springbooks.config.readers;

import com.ots.springbooks.models.jpa.BookJpa;
import com.ots.springbooks.repositories.jpa.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookReader implements ItemReader<BookJpa> {

  private final BookRepository bookJpaRepository;
  private ItemReader<BookJpa> reader;

  @Override
  public BookJpa read() throws Exception {
    if (reader == null) {
      reader = new IteratorItemReader<>(bookJpaRepository.findAll());
    }
    return reader.read();
  }
}
