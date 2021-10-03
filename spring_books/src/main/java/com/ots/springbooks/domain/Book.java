package com.ots.springbooks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

  private long id;
  private String title;
  //  private String publishedDate;
  private Author author;
  private Genre genre;

  public Book(String title, Author author, Genre genre) {
    this.title = title;
    this.author = author;
    this.genre = genre;
  }
}
