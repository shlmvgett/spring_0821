package com.ots.springbooks.repositories.impl;

import com.ots.springbooks.models.Book;
import com.ots.springbooks.repositories.BookRepository;
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
        em.createQuery("select b from Book b join fetch b.genre join fetch b.author", Book.class);
    return query.getResultList();
  }

  @Override
  public Optional<Book> findById(long id) {
    Book book = em.find(Book.class, id);
    return Optional.ofNullable(book);
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
  public void deleteById(Book book) {
    em.remove(book);
  }
}
