package com.ots.springbooks.models;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

  @Id private String id;
  private String title;
  @DBRef private Author author;
  @DBRef private Genre genre;

  public Book(String title, Author author, Genre genre) {
    this.title = title;
    this.author = author;
    this.genre = genre;
  }

  public Book(UUID id, String title, Author author, Genre genre) {
    this.id = id.toString();
    this.title = title;
    this.author = author;
    this.genre = genre;
  }
}
