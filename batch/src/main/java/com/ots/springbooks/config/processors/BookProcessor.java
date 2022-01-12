package com.ots.springbooks.config.processors;

import com.ots.springbooks.models.jpa.BookJpa;
import com.ots.springbooks.models.mongo.AuthorMongo;
import com.ots.springbooks.models.mongo.BookMongo;
import com.ots.springbooks.models.mongo.GenreMongo;
import com.ots.springbooks.repositories.mongo.AuthorMongoRepository;
import com.ots.springbooks.repositories.mongo.GenreMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookProcessor implements ItemProcessor<BookJpa, BookMongo> {

  private final AuthorMongoRepository authorMongoRepository;
  private final GenreMongoRepository genreMongoRepository;

  @Override
  public BookMongo process(BookJpa bookJpa) {
    AuthorMongo authorMongo = authorMongoRepository.findByName(bookJpa.getAuthor().getName()).get();
    GenreMongo genreMongo = genreMongoRepository.findByTitle(bookJpa.getGenre().getTitle()).get();
    return new BookMongo(bookJpa.getTitle(), authorMongo, genreMongo);
  }
}
