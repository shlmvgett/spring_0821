package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
