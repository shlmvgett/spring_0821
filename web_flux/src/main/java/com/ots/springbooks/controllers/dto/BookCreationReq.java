package com.ots.springbooks.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BookCreationReq {

  private String title;
  private String authorId;
  private String genreId;
}
