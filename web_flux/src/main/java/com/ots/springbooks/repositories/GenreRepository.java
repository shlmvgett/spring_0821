package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {}
