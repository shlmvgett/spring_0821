package com.ots.springsurvey.services;

import com.ots.springsurvey.domain.Answer;
import com.ots.springsurvey.domain.Question;
import com.ots.springsurvey.domain.Survey;
import com.ots.springsurvey.service.IOService;
import com.ots.springsurvey.service.ResultServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ResultServiceImpl.class)
class ResultServiceImplTests {

  @MockBean
  private IOService ioService;

  @Autowired
  private ResultServiceImpl resultServiceImpl;

  @Test
  void checkCorrectResultTest() {
    Survey survey = new Survey();
    survey.setQuestions(List.of(new Question(1, "question", 1, List.of("answer"))));
    survey.setAnswers(List.of(new Answer(1, 1)));
    resultServiceImpl.checkResult(survey);
    assertThat(survey.getAnswers().get(0).isRight()).isTrue();
  }

  @Test
  void checkIncorrectResultTest() {
    Survey survey = new Survey();
    survey.setQuestions(List.of(new Question(1, "question", 1, List.of("answer"))));
    survey.setAnswers(List.of(new Answer(1, 2)));
    resultServiceImpl.checkResult(survey);
    assertThat(survey.getAnswers().get(0).isRight()).isFalse();
  }

  @Test
  void printResultTest() {
    Survey survey = new Survey();
    survey.setQuestions(List.of(new Question(1, "question", 1, List.of("answer"))));
    survey.setAnswers(List.of(new Answer(1, 1)));
    resultServiceImpl.checkResult(survey);
    resultServiceImpl.printResult(survey);
    verify(ioService, times(1)).out("\nResults:");
  }
}
