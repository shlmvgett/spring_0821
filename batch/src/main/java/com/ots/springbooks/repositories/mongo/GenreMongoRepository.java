package com.ots.springbooks.repositories.mongo;

import com.ots.springbooks.models.mongo.GenreMongo;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreMongoRepository extends MongoRepository<GenreMongo, String> {

  Optional<GenreMongo> findByTitle(String title);
}
