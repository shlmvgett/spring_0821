package com.ots.springbooks.repositories.mongo;

import com.ots.springbooks.models.mongo.BookMongo;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookMongoRepository extends MongoRepository<BookMongo, String> {

  Optional<BookMongo> findByTitle(String title);
}
