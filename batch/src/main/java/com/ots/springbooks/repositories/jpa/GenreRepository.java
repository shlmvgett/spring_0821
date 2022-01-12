package com.ots.springbooks.repositories.jpa;

import com.ots.springbooks.models.jpa.GenreJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreJpa, Long> {}
