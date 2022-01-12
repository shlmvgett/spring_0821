package com.ots.springbooks.models.mongo;

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
public class AuthorMongo {

  @Id private String id;
  private String name;

  public AuthorMongo(String name) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
  }
}
