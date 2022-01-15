package ru.otus.spring.integration.domain;

import lombok.Getter;

@Getter
public class Date {

  private final Integer month;

  public Date(Integer month) {
    this.month = month;
  }
}
