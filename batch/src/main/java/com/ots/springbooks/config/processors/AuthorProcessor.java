package com.ots.springbooks.config.processors;

import com.ots.springbooks.models.jpa.AuthorJpa;
import com.ots.springbooks.models.mongo.AuthorMongo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class AuthorProcessor implements ItemProcessor<AuthorJpa, AuthorMongo> {

  @Override
  public AuthorMongo process(AuthorJpa authorJpa) {
    return new AuthorMongo(authorJpa.getName());
  }
}
