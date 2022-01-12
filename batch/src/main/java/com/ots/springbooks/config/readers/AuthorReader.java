package com.ots.springbooks.config.readers;

import com.ots.springbooks.models.jpa.AuthorJpa;
import com.ots.springbooks.repositories.jpa.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorReader implements ItemReader<AuthorJpa> {

  private final AuthorRepository authorRepository;
  private ItemReader<AuthorJpa> reader;

  @Override
  public AuthorJpa read() throws Exception {
    if (reader == null) {
      reader = new IteratorItemReader<>(authorRepository.findAll());
    }
    return reader.read();
  }
}
