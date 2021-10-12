package com.ots.springbooks.repositories.impl;

import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.GenreRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

  @PersistenceContext private final EntityManager em;

  @Override
  public List<Genre> findAll() {
    TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
    return query.getResultList();
  }

  @Override
  public Optional<Genre> findById(long id) {
    Genre genre = em.find(Genre.class, id);
    return Optional.ofNullable(genre);
  }

  @Override
  public Genre save(Genre genre) {
    if (genre.getId() <= 0) {
      em.persist(genre);
      return genre;
    } else {
      return em.merge(genre);
    }
  }

  @Override
  public void deleteById(Genre genre) {
    em.remove(genre);
  }
}
