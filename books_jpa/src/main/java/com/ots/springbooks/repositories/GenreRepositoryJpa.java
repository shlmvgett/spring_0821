package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.interfaces.GenreRepository;
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
    TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.id = :id", Genre.class);
    query.setParameter("id", id);
    try {
      return Optional.of(query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
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
  public void updateTitleById(long id, String title) {
    Query query = em.createQuery("update Genre g set g.title = :title where g.id = :id");
    query.setParameter("title", title);
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  public void deleteById(long id) {
    Query query = em.createQuery("delete from Genre g where g.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }
}
