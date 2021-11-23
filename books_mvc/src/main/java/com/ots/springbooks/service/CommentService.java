package com.ots.springbooks.service;

import com.ots.springbooks.models.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {

  List<Comment> getAll();

  Optional<Comment> getById(Long commentId);

  List<Comment> getCommentsByBookId(Long bookId);

  Comment insert(Comment comment);
}
