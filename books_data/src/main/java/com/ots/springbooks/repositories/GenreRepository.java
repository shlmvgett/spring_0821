package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {}
