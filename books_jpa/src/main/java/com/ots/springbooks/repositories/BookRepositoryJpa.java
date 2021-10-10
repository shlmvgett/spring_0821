package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Book;
import com.ots.springbooks.repositories.interfaces.BookRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
@AllArgsConstructor
public class BookRepositoryJpa implements BookRepository {

  @PersistenceContext private final EntityManager em;

  @Override
  public List<Book> findAll() {
    TypedQuery<Book> query =
        em.createQuery(
            "select b from Book b join fetch b.comments join fetch b.genre join fetch b.author",
            Book.class);
    return query.getResultList();
  }

  @Override
  public Optional<Book> findById(long id) {
    TypedQuery<Book> query =
        em.createQuery(
            "select b from Book b join fetch b.comments join fetch b.genre join fetch b.author where b.id = :id",
            Book.class);
    query.setParameter("id", id);
    try {
      return Optional.of(query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Book> findByTitle(String title) {
    TypedQuery<Book> query =
        em.createQuery(
            "select b from Book b join fetch b.comments join fetch b.genre join fetch b.author where b.title like :title",
            Book.class);
    query.setParameter("title", title);
    return query.getResultList();
  }

  @Override
  public Book save(Book book) {
    if (book.getId() <= 0) {
      em.persist(book);
      return book;
    } else {
      return em.merge(book);
    }
  }

  @Override
  public void updateTitleById(long id, String title) {
    Query query = em.createQuery("update Book b set b.title = :title where b.id = :id");
    query.setParameter("title", title);
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  public void deleteById(long id) {
    Query query = em.createQuery("delete from Book b where b.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }
}
