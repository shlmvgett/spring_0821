package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  Optional<Book> findByTitle(String title);
}
