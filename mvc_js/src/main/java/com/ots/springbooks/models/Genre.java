package com.ots.springbooks.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genres")
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title", nullable = false)
  private String title;

  public Genre(String title) {
    this.title = title;
  }

  public Genre(long id) {
    this.id = id;
  }
}
