package com.ots.springbooks.models;

import com.ots.springbooks.controllers.dto.BookCreationDto;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "owner", nullable = false)
  private String owner;

  @JoinColumn(name = "author_id")
  @OneToOne(targetEntity = Author.class)
  private Author author;

  @JoinColumn(name = "genre_id")
  @OneToOne(targetEntity = Genre.class)
  private Genre genre;

  @JoinColumn(name = "book_id")
  @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Comment> comments;

  public Book(String title, String owner, Author author, Genre genre) {
    this.title = title;
    this.owner = owner;
    this.author = author;
    this.genre = genre;
  }

  public Book(long id, String title, Author author, Genre genre) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.genre = genre;
  }

  public Book(BookCreationDto bookCreationDto) {
    this.title = bookCreationDto.getTitle();
    this.owner = bookCreationDto.getOwner();
    this.author = new Author(bookCreationDto.getAuthorId());
    this.genre = new Genre(bookCreationDto.getGenreId());
  }
}
