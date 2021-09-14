package com.ots.springsurvey.domain;

import com.ots.springsurvey.dao.QuestionsDaoImpl;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Survey {

  private String participant;
  private List<Question> questions;

  public Survey() {
    this.questions = new QuestionsDaoImpl().getContent();
  }
}
