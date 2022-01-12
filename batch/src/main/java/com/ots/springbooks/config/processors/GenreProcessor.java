package com.ots.springbooks.config.processors;

import com.ots.springbooks.models.jpa.GenreJpa;
import com.ots.springbooks.models.mongo.GenreMongo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class GenreProcessor implements ItemProcessor<GenreJpa, GenreMongo> {

  @Override
  public GenreMongo process(GenreJpa genreJpa) {
    return new GenreMongo(genreJpa.getTitle());
  }
}
