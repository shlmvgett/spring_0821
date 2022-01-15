package ru.otus.spring.integration.domain;

import lombok.Getter;

@Getter
public class RenovationStage {

  private final String title;

  public RenovationStage(String title) {
    this.title = title;
  }
}
