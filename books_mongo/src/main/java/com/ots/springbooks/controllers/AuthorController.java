package com.ots.springbooks.controllers;

import com.ots.springbooks.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class AuthorController {

  private AuthorService authorService;

  @ShellMethod(
      value = "Get all Authors",
      key = {"gaa", "get_authors_all"})
  public void getAllAuthors() {
    authorService.getAllAuthors();
  }

  @ShellMethod(
      value = "Get Author by Id",
      key = {"gai", "get_author_id"})
  public void getAuthorById() {
    authorService.getAuthorById();
  }

  @ShellMethod(
      value = "Get Author by Name",
      key = {"gan", "get_author_name"})
  public void getAuthorByName() {
    authorService.getAuthorByName();
  }

  @ShellMethod(
      value = "Insert Author",
      key = {"ia", "insert_authors"})
  public void insertAuthor() {
    authorService.insertAuthor();
  }

  @ShellMethod(
      value = "Update Author",
      key = {"ua", "update_authors"})
  public void updateAuthor() {
    authorService.updateAuthor();
  }

  @ShellMethod(
      value = "Delete Authors",
      key = {"da", "delete_authors"})
  public void deleteAuthor() {
    authorService.deleteAuthor();
  }
}
