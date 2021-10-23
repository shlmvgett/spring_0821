package com.ots.springbooks.models;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {

  @Id private String id;
  private String name;

  public Author(String name) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
  }

  public Author(UUID id, String name) {
    this.id = id.toString();
    this.name = name;
  }
}
