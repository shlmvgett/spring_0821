package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {}
