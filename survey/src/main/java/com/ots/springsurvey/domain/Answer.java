package com.ots.springsurvey.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Answer {

  private final Integer questionId;
  private final Integer answer;
  @Setter private boolean isRight;

  public Answer(Integer questionId, Integer answer) {
    this.questionId = questionId;
    this.answer = answer;
  }
}
