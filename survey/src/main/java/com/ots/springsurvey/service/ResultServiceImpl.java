package com.ots.springsurvey.service;

import com.ots.springsurvey.domain.Answer;
import com.ots.springsurvey.domain.Survey;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

  private final IOService ioService;

  public ResultServiceImpl(IOService ioService) {
    this.ioService = ioService;
  }

  @Override
  public void checkResult(Survey survey) {
    survey.getAnswers().forEach(a -> {
      int questionId = a.getQuestionId();
      a.setRight(survey.getQuestionById(questionId).getRightAnswer() == a.getAnswer());
    });
  }

  @Override
  public void printResult(Survey survey) {
    ioService.out("strings.result.title");
    for (Answer answer : survey.getAnswers()) {
      ioService.out("strings.result.question",
          survey.getQuestionById(answer.getQuestionId()).getText(), answer.isRight());
    }
  }
}
