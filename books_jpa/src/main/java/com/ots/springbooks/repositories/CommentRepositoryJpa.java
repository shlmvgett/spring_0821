package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Comment;
import com.ots.springbooks.repositories.interfaces.CommentRepository;
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
    TypedQuery<Comment> query =
        em.createQuery("select c from Comment c where c.id = :id", Comment.class);
    query.setParameter("id", id);
    try {
      return Optional.of(query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
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
  public void updateById(long id, String text) {
    Query query = em.createQuery("update Comment c set c.text = :text where c.id = :id");
    query.setParameter("text", text);
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  public void deleteById(long id) {
    Query query = em.createQuery("delete from Comment c where c.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }
}
