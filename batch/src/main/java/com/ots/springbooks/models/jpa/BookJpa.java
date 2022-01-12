package com.ots.springbooks.models.jpa;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "books")
public class BookJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title", nullable = false, unique = false)
  private String title;

  @JoinColumn(name = "author_id")
  @OneToOne(targetEntity = AuthorJpa.class)
  private AuthorJpa author;

  @JoinColumn(name = "genre_id")
  @OneToOne(targetEntity = GenreJpa.class)
  private GenreJpa genre;
}
