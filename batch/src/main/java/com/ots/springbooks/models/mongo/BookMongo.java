package com.ots.springbooks.models.mongo;

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
public class BookMongo {

  @Id private String id;
  private String title;
  @DBRef private AuthorMongo author;
  @DBRef private GenreMongo genre;

  public BookMongo(String title, AuthorMongo author, GenreMongo genre) {
    this.id = UUID.randomUUID().toString();
    this.title = title;
    this.author = author;
    this.genre = genre;
  }
}
