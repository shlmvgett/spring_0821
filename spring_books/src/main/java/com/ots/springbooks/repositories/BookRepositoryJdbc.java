package com.ots.springbooks.repositories;

import com.ots.springbooks.domain.Author;
import com.ots.springbooks.domain.Book;
import com.ots.springbooks.domain.Genre;
import com.ots.springbooks.repositories.interfaces.BookRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
@AllArgsConstructor
public class BookRepositoryJdbc implements BookRepository {

  private final JdbcOperations jdbc;
  private final NamedParameterJdbcOperations namedJdbcOperations;

  @Override
  public List<Book> getAll() {
    return jdbc.query(
        "select b.id, b.title, "
            + "a.id as author_id, a.name as author_name, "
            + "g.id as genre_id, g.title as genre_title "
            + "from books b "
            + "join authors a on b.author_id = a.id "
            + "join genres g on b.genre_id = g.id",
        new BookMapper());
  }

  @Override
  public Book getById(long id) {
    Map<String, Object> params = new HashMap<>(Map.of("id", id));
    try {
      return namedJdbcOperations.queryForObject(
          "select b.id, b.title, "
              + "a.id as author_id, a.name as author_name, "
              + "g.id as genre_id, g.title as genre_title "
              + "from books b "
              + "join authors a on b.author_id = a.id "
              + "join genres g on b.genre_id = g.id "
              + "where b.id = :id",
          params,
          new BookMapper());
    } catch (EmptyResultDataAccessException ex) {
      return null;
    }
  }

  @Override
  public void insert(Book book) {
    Map<String, Object> params = new HashMap<>();
    params.put("title", book.getTitle());
    params.put("author_id", book.getAuthor().getId());
    params.put("genre_id", book.getGenre().getId());
    namedJdbcOperations.update(
        "insert into books (title, author_id, genre_id) "
            + "values (:title, :author_id, :genre_id)",
        params);
  }

  @Override
  public void update(Book book) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", book.getId());
    params.put("title", book.getTitle());
    params.put("author_id", book.getAuthor().getId());
    params.put("genre_id", book.getGenre().getId());
    namedJdbcOperations.update(
        "update books "
            + "set title = (:title), "
            + "author_id = (:author_id), "
            + "genre_id = (:genre_id) "
            + "where id = (:id)",
        params);
  }

  @Override
  public void delete(Book book) {
    Map<String, Object> params = new HashMap<>(Map.of("id", book.getId()));
    namedJdbcOperations.update("delete from books where id = :id", params);
  }

  private static class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
      long id = resultSet.getLong("id");
      String title = resultSet.getString("title");

      String authorName = resultSet.getString("author_name");
      long authorId = resultSet.getLong("author_id");

      String genreTitle = resultSet.getString("genre_title");
      long genreId = resultSet.getLong("genre_id");

      return new Book(id, title, new Author(authorId, authorName), new Genre(genreId, genreTitle));
    }
  }
}
