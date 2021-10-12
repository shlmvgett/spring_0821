package com.ots.springbooks.repositories.impl;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.repositories.AuthorRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {

  @PersistenceContext private final EntityManager em;

  @Override
  public List<Author> findAll() {
    TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
    return query.getResultList();
  }

  @Override
  public Optional<Author> findById(long id) {
    Author author = em.find(Author.class, id);
    return Optional.ofNullable(author);
  }

  @Override
  public Author save(Author author) {
    if (author.getId() <= 0) {
      em.persist(author);
      return author;
    } else {
      return em.merge(author);
    }
  }

  @Override
  public void deleteById(Author author) {
    em.remove(author);
  }
}
