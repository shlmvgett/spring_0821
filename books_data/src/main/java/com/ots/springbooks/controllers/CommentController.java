package com.ots.springbooks.controllers;

import com.ots.springbooks.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class CommentController {

  private CommentService commentService;

  @ShellMethod(
      value = "Get all Comments",
      key = {"gac"})
  public void getAllComments() {
    commentService.getAll();
  }

  @ShellMethod(
      value = "Get Comment by Id",
      key = {"gci"})
  public void getCommentById() {
    commentService.getById();
  }

  @ShellMethod(
      value = "Get Comment by book Id",
      key = {"gcbi"})
  public void getCommentsByBookId() {
    commentService.getCommentsByBookId();
  }

  @ShellMethod(
      value = "Insert Comment",
      key = {"ic"})
  public void insertComment() {
    commentService.insert();
  }

  @ShellMethod(
      value = "Update Comment",
      key = {"uc"})
  public void updateComment() {
    commentService.update();
  }

  @ShellMethod(
      value = "Delete Comment",
      key = {"dc"})
  public void deleteComment() {
    commentService.delete();
  }
}
