package com.ots.springbooks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

  private long id;
  private String name;

  public Author(String name) {
    this.name = name;
  }
}
