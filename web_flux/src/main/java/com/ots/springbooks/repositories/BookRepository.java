package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {}
