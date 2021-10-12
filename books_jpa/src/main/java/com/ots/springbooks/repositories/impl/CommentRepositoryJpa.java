package com.ots.springbooks.repositories.impl;

import com.ots.springbooks.models.Comment;
import com.ots.springbooks.repositories.CommentRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

  @PersistenceContext private final EntityManager em;

  @Override
  public List<Comment> findAll() {
    TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
    return query.getResultList();
  }

  @Override
  public Optional<Comment> findById(long id) {
    Comment comment = em.find(Comment.class, id);
    return Optional.ofNullable(comment);
  }

  @Override
  public Comment save(Comment comment) {
    if (comment.getId() <= 0) {
      em.persist(comment);
      return comment;
    } else {
      return em.merge(comment);
    }
  }

  @Override
  public void deleteById(Comment comment) {
    em.remove(comment);
  }
}
