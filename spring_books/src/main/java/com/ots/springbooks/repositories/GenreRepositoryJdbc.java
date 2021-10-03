package com.ots.springbooks.repositories;

import com.ots.springbooks.domain.Genre;
import com.ots.springbooks.repositories.interfaces.GenreRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class GenreRepositoryJdbc implements GenreRepository {

  private final JdbcOperations jdbc;
  private final NamedParameterJdbcOperations namedJdbcOperations;

  @Override
  public List<Genre> getAll() {
    return jdbc.query("select id, title from genres", new GenreRepositoryJdbc.Mapper());
  }

  @Override
  public Genre getById(long id) {
    Map<String, Object> params = new HashMap<>(Map.of("id", id));
    try {
      return namedJdbcOperations.queryForObject(
          "select * from genres where id = :id", params, new GenreRepositoryJdbc.Mapper());
    } catch (EmptyResultDataAccessException ex) {
      return null;
    }
  }

  @Override
  public void insert(Genre genre) {
    Map<String, Object> params = new HashMap<>(Map.of("title", genre.getTitle()));
    namedJdbcOperations.update("insert into genres (title) values (:title)", params);
  }

  @Override
  public void update(Genre genre) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", genre.getId());
    params.put("title", genre.getTitle());
    namedJdbcOperations.update("update genres set title = (:title) where id = (:id)", params);
  }

  @Override
  public void delete(Genre genre) {
    Map<String, Object> params = new HashMap<>(Map.of("id", genre.getId()));
    namedJdbcOperations.update("delete from genres where id = :id", params);
  }

  private static class Mapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
      long id = resultSet.getLong("id");
      String title = resultSet.getString("title");
      return new Genre(id, title);
    }
  }
}
