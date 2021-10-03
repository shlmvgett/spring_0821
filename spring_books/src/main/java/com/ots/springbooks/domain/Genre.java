package com.ots.springbooks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {

  private long id;
  private String title;

  public Genre(String title) {
    this.title = title;
  }
}
