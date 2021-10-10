package com.ots.springbooks.controllers;

import com.ots.springbooks.service.interfaces.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class GenreController {

  private GenreService genreService;

  @ShellMethod(
      value = "Get all Genre",
      key = {"gag"})
  public void getAllGenres() {
    genreService.getAllGenres();
  }

  @ShellMethod(
      value = "Get Genre by Id",
      key = {"ggi"})
  public void getGenreById() {
    genreService.getGenreById();
  }

  @ShellMethod(
      value = "Insert Genre",
      key = {"ig"})
  public void insertGenre() {
    genreService.insertGenre();
  }

  @ShellMethod(
      value = "Update Genre",
      key = {"ug"})
  public void updateGenre() {
    genreService.updateGenre();
  }

  @ShellMethod(
      value = "Delete Genre",
      key = {"dg"})
  public void deleteGenre() {
    genreService.deleteGenre();
  }
}
