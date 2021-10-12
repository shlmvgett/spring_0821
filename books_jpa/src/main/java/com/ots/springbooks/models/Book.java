package com.ots.springbooks.models;

import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title", nullable = false, unique = false)
  private String title;

  @JoinColumn(name = "author_id")
  @OneToOne(targetEntity = Author.class)
  private Author author;

  @JoinColumn(name = "genre_id")
  @OneToOne(targetEntity = Genre.class)
  private Genre genre;

  @JoinColumn(name = "book_id")
  @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Comment> comments;

  public Book(String title, Author author, Genre genre) {
    this.title = title;
    this.author = author;
    this.genre = genre;
  }

  public Book(long id, String title, Author author, Genre genre) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.genre = genre;
  }
}
