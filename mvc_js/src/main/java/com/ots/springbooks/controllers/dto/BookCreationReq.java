package com.ots.springbooks.controllers.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BookCreationReq {

  private String title;
  private long authorId;
  private long genreId;
}
