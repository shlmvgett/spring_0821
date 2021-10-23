package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Author;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

  Optional<Author> findByName(String name);
}
