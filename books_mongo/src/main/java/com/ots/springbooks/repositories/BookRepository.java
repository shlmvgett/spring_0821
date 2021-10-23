package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Book;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

  Optional<Book> findByTitle(String title);
}
