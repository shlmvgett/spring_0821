package com.ots.springsurvey.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Survey {

  private String participant;
  private List<Question> questions;
  private List<Answer> answers = new ArrayList<>();

  public Survey() {
  }

  public void addAnswer(Answer answer) {
    answers.add(answer);
  }

  public Question getQuestionById(int id) {
    return questions.stream().filter(q -> q.getId() == id).findFirst()
        .orElseThrow(() -> new RuntimeException("Question with id " + id + "wasn't found"));
  }
}
