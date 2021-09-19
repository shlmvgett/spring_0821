package com.ots.springsurvey.service;

import com.ots.springsurvey.dao.QuestionsDao;
import com.ots.springsurvey.domain.Answer;
import com.ots.springsurvey.domain.Question;
import com.ots.springsurvey.domain.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SurveyServiceImpl implements SurveyService {

  private final Survey survey = new Survey();
  private final QuestionsDao questionsDao;
  private final IOService ioService;
  private final ResultService resultService;

  @Autowired
  public SurveyServiceImpl(QuestionsDao questionsDao, IOService ioService, ResultService resultService) {
    this.questionsDao = questionsDao;
    this.ioService = ioService;
    this.resultService = resultService;
  }

  @Override
  public void run() {
    inputParticipantName();
    startSurvey();
    calculateResult();
  }

  @Override
  public void inputParticipantName() {
    survey.setParticipant(ioService.in("strings.input.name").nextLine());
  }

  @Override
  public void startSurvey() {
    survey.setQuestions(questionsDao.getQuestions());
    for (Question question : survey.getQuestions()) {
      ioService.out("strings.question.text", question.getText());
      ioService.out("strings.question.answers", question.getAnswers().toString());
      ioService.out("strings.question.response");
      Scanner in = ioService.in();
      while (!in.hasNextInt()) {
        ioService.out("strings.question.response.error");
        in = new Scanner(System.in);
      }
      int answerNumber = in.nextInt();
      survey.addAnswer(new Answer(question.getId(), answerNumber));
    }
  }

  @Override
  public void calculateResult() {
    resultService.checkResult(survey);
    resultService.printResult(survey);
  }
}
