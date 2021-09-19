package com.ots.springsurvey.service;

import com.ots.springsurvey.domain.Survey;

public interface ResultService {

  void checkResult(Survey survey);

  void printResult(Survey survey);
}
