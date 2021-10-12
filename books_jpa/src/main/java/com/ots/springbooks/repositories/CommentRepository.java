package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {

  List<Comment> findAll();

  Optional<Comment> findById(long id);

  Comment save(Comment comment);

  void deleteById(Comment comment);
}
