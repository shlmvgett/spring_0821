package com.ots.springbooks.repositories.jpa;

import com.ots.springbooks.models.jpa.AuthorJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorJpa, Long> {}
