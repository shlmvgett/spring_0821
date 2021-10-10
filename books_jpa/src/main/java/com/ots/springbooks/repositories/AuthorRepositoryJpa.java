package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.repositories.interfaces.AuthorRepository;
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
    TypedQuery<Author> query =
        em.createQuery("select a from Author a where a.id = :id", Author.class);
    query.setParameter("id", id);
    try {
      return Optional.of(query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
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
  public void updateNameById(long id, String name) {
    Query query = em.createQuery("update Author a set a.name = :name where a.id = :id");
    query.setParameter("name", name);
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  public void deleteById(long id) {
    Query query = em.createQuery("delete from Author a where a.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }
}
