package com.ots.springsurvey.dao;

import com.ots.springsurvey.domain.Question;

import java.util.List;

public interface QuestionsDao {

  List<Question> getQuestions();
}
