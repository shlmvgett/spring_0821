package com.ots.springbooks.repositories.jpa;

import com.ots.springbooks.models.jpa.BookJpa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookJpa, Long> {

  Optional<BookJpa> findByTitle(String title);
}
