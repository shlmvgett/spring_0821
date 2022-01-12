package com.ots.springbooks.config.readers;

import com.ots.springbooks.models.jpa.GenreJpa;
import com.ots.springbooks.repositories.jpa.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreReader implements ItemReader<GenreJpa> {

  private final GenreRepository genreJpaRepository;
  private ItemReader<GenreJpa> reader;

  @Override
  public GenreJpa read() throws Exception {
    if (reader == null) {
      reader = new IteratorItemReader<>(genreJpaRepository.findAll());
    }
    return reader.read();
  }
}
