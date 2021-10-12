package com.ots.springbooks.controllers;

import com.ots.springbooks.service.BookService;
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
  public void getAllBooks() {
    bookService.getAllBooks();
  }

  @ShellMethod(
      value = "Get Book by Id",
      key = {"gbi"})
  public void getBookById() {
    bookService.getBookById();
  }

  @ShellMethod(
      value = "Get Book by Title",
      key = {"gbt"})
  public void getBookByTitle() {
    bookService.getBookByTitle();
  }

  @ShellMethod(
      value = "Insert Book",
      key = {"ib"})
  public void insertBook() {
    bookService.insertBook();
  }

  @ShellMethod(
      value = "Update Book",
      key = {"ub"})
  public void updateBook() {
    bookService.updateBook();
  }

  @ShellMethod(
      value = "Delete Book",
      key = {"db"})
  public void deleteBook() {
    bookService.deleteBook();
  }
}
