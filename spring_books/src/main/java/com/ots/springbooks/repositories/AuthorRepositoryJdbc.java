package com.ots.springbooks.repositories;

import com.ots.springbooks.domain.Author;
import com.ots.springbooks.repositories.interfaces.AuthorRepository;
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
public class AuthorRepositoryJdbc implements AuthorRepository {

  private final JdbcOperations jdbc;
  private final NamedParameterJdbcOperations namedJdbcOperations;

  @Override
  public List<Author> getAll() {
    return jdbc.query("select id, name from authors", new AuthorMapper());
  }

  @Override
  public Author getById(long id) {
    Map<String, Object> params = new HashMap<>(Map.of("id", id));
    try {
      return namedJdbcOperations.queryForObject(
          "select * from authors where id = :id", params, new AuthorMapper());
    } catch (EmptyResultDataAccessException ex) {
      return null;
    }
  }

  @Override
  public void insert(Author author) {
    Map<String, Object> params = new HashMap<>(Map.of("name", author.getName()));
    namedJdbcOperations.update("insert into authors (name) values (:name)", params);
  }

  @Override
  public void update(Author author) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", author.getId());
    params.put("name", author.getName());
    namedJdbcOperations.update("update authors set name = (:name) where id = (:id)", params);
  }

  @Override
  public void delete(Author author) {
    Map<String, Object> params = new HashMap<>(Map.of("id", author.getId()));
    namedJdbcOperations.update("delete from authors where id = :id", params);
  }

  private static class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
      long id = resultSet.getLong("id");
      String name = resultSet.getString("name");
      return new Author(id, name);
    }
  }
}
