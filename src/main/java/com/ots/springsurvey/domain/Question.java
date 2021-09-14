package com.ots.springsurvey.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Question {

  private String text;
  private List<String> answers;
  private int rightAnswer;
  private boolean isRightAnswered;

  public Question(String text, int rightAnswer, List<String> answers) {
    this.text = text;
    this.answers = answers;
    this.rightAnswer = rightAnswer;
  }
}
