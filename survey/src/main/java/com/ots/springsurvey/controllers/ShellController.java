package com.ots.springsurvey.controllers;

import com.ots.springsurvey.dao.QuestionsDao;
import com.ots.springsurvey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {

  private final SurveyService surveyService;
  private final QuestionsDao questionsDao;

  @ShellMethod(value = "Start survey ", key = {"s", "start"})
  public void startSurvey() {
    surveyService.run();
  }

  @ShellMethod(value = "Get all questions", key = {"a", "all"})
  public String getAllQuestions() {
    return questionsDao.getQuestions().toString();
  }
}
