package com.ots.springbooks.service.impl;

import com.ots.springbooks.models.Comment;
import com.ots.springbooks.repositories.BookRepository;
import com.ots.springbooks.repositories.CommentRepository;
import com.ots.springbooks.service.CommentService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final BookRepository bookRepository;

  @Override
  public List<Comment> getAll() {
    return commentRepository.findAll();
  }

  @Override
  public Optional<Comment> getById(Long commentId) {
    return commentRepository.findById(commentId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Comment> getCommentsByBookId(Long bookId) {
    return bookRepository.findById(bookId).get().getComments();
  }

  @Override
  @Transactional
  public Comment insert(Comment comment) {
    return commentRepository.save(comment);
  }
}
