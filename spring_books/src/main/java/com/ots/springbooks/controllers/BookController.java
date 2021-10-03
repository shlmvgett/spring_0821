package com.ots.springbooks.controllers;

import com.ots.springbooks.service.interfaces.BookService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class BookController {

  private BookService bookService;

  @ShellMethod(
      value = "Get all Books",
      key = {"gab"})
  public void getAllAuthors() {
    bookService.getAllBooks();
  }

  @ShellMethod(
      value = "Get Book by Id",
      key = {"gbi"})
  public void getAuthorById() {
    bookService.getBookById();
  }

  @ShellMethod(
      value = "Insert Book",
      key = {"ib"})
  public void insertAuthor() {
    bookService.insertBook();
  }

  @ShellMethod(
      value = "Update Book",
      key = {"ub"})
  public void updateAuthor() {
    bookService.updateBook();
  }

  @ShellMethod(
      value = "Delete Book",
      key = {"db"})
  public void deleteAuthor() {
    bookService.deleteBook();
  }
}
