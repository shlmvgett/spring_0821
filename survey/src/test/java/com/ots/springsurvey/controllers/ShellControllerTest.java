package com.ots.springsurvey.controllers;

import com.ots.springsurvey.dao.QuestionsDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShellControllerTest {

  @Autowired
  private Shell shell;

  @Autowired
  private QuestionsDao questionsDao;

  @Test
  @DisplayName("Должен возвращать все вопросы")
  void shouldReturnAllQuestionsTest() {
    String res = (String) shell.evaluate(() -> "a");
    assertThat(res).isEqualTo(questionsDao.getQuestions().toString());

    res = (String) shell.evaluate(() -> "all");
    assertThat(res).isEqualTo(questionsDao.getQuestions().toString());
  }
}