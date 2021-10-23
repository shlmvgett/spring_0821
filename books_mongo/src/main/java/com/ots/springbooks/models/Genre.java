package com.ots.springbooks.models;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "gernes")
public class Genre {

  @Id private String id;
  private String title;

  public Genre(String title) {
    this.id = UUID.randomUUID().toString();
    this.title = title;
  }

  public Genre(UUID id, String title) {
    this.id = id.toString();
    this.title = title;
  }
}
