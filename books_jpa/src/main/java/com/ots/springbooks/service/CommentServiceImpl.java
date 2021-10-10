package com.ots.springbooks.service;

import com.ots.springbooks.models.Comment;
import com.ots.springbooks.repositories.interfaces.CommentRepository;
import com.ots.springbooks.service.interfaces.CommentService;
import com.ots.springbooks.service.interfaces.IOService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final IOService ioService;
  private final CommentRepository commentRepository;

  @Override
  @Transactional
  public void getAll() {
    List<Comment> comments = commentRepository.findAll();
    if (!CollectionUtils.isEmpty(comments)) {
      comments.forEach(genre -> ioService.print(genre.toString()));
    } else {
      ioService.print("comments table is empty.");
    }
  }

  @Override
  @Transactional
  public void getById() {
    ioService.print("Type comment id:");
    long commentId = Long.parseLong(ioService.read());
    Optional<Comment> comment = commentRepository.findById(commentId);
    if (comment.isPresent()) {
      ioService.print(comment.toString());
    } else {
      ioService.print("comment wasn't found");
    }
  }

  @Override
  @Transactional
  public void insert() {
    ioService.print("Type comment name:");
    String comment = ioService.read();
    commentRepository.save(new Comment(comment));
    ioService.print("comment was added");
  }

  @Override
  @Transactional
  public void update() {
    ioService.print("Type comment id:");
    long genreId = Long.parseLong(ioService.read());
    Optional<Comment> comment = commentRepository.findById(genreId);
    if (comment.isEmpty()) {
      ioService.print("comment with Id:" + genreId + " wasn't found");
    } else {
      ioService.print("Type comment text:");
      String genreName = ioService.read();
      commentRepository.updateById(comment.get().getId(), genreName);
      ioService.print("comment was updated");
    }
  }

  @Override
  @Transactional
  public void delete() {
    ioService.print("Type comment id:");
    long genreId = Long.parseLong(ioService.read());
    Optional<Comment> comment = commentRepository.findById(genreId);
    if (comment.isPresent()) {
      commentRepository.deleteById(comment.get().getId());
      ioService.print("comment was deleted");
    } else {
      ioService.print("comment wasn't found");
    }
  }
}
