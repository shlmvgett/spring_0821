package com.ots.springbooks.controllers;

import com.ots.springbooks.service.interfaces.CommentService;
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
  public void getAllGenres() {
    commentService.getAll();
  }

  @ShellMethod(
      value = "Get Comment by Id",
      key = {"gci"})
  public void getGenreById() {
    commentService.getById();
  }

  @ShellMethod(
      value = "Insert Comment",
      key = {"ic"})
  public void insertGenre() {
    commentService.insert();
  }

  @ShellMethod(
      value = "Update Comment",
      key = {"uc"})
  public void updateGenre() {
    commentService.update();
  }

  @ShellMethod(
      value = "Delete Comment",
      key = {"dc"})
  public void deleteGenre() {
    commentService.delete();
  }
}
