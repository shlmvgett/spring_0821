package com.ots.springbooks.repositories.mongo;

import com.ots.springbooks.models.mongo.AuthorMongo;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorMongoRepository extends MongoRepository<AuthorMongo, String> {

  Optional<AuthorMongo> findByName(String name);
}
