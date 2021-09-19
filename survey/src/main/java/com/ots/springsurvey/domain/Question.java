package com.ots.springsurvey.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class Question {

  private final int id;
  private final String text;
  private final List<String> answers;
  private final int rightAnswer;

  public Question(int id, String text, int rightAnswer, List<String> answers) {
    this.id = id;
    this.text = text;
    this.answers = answers;
    this.rightAnswer = rightAnswer;
  }
}
