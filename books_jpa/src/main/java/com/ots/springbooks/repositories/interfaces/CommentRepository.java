package com.ots.springbooks.repositories.interfaces;

import com.ots.springbooks.models.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {

  List<Comment> findAll();

  Optional<Comment> findById(long id);
  //  Optional<Comment> findByBookId(long bookId);

  Comment save(Comment comment);

  void updateById(long id, String text);

  void deleteById(long id);
}
