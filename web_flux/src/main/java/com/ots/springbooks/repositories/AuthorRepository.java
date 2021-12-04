package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {}
