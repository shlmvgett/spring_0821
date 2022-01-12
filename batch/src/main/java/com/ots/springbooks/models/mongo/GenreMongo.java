package com.ots.springbooks.models.mongo;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "gernes")
public class GenreMongo {

  @Id private String id;
  private String title;

  public GenreMongo(String title) {
    this.id = UUID.randomUUID().toString();
    this.title = title;
  }
}
