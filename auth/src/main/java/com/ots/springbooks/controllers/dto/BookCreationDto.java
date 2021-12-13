package com.ots.springbooks.controllers.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookCreationDto {

  private String title;
  private String owner;
  private long authorId;
  private long genreId;
}
