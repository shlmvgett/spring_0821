package com.ots.springsurvey.service;

import org.springframework.stereotype.Service;
import com.ots.springsurvey.domain.*;

import java.util.Scanner;

@Service
public class SurveyServiceImpl implements SurveyService {

  private final Survey survey;

  public SurveyServiceImpl(Survey survey) {
    this.survey = survey;
  }

  @Override
  public void inputParticipantName() {
    Scanner in = new Scanner(System.in);
    System.out.print("Input your name: ");
    survey.setParticipant(in.nextLine());
  }

  @Override
  public void startSurvey() {
    for (Question question : survey.getQuestions()) {
      System.out.println("\nQuestion: " + question.getText());
      System.out.println("Answers: " + question.getAnswers());
      System.out.print("Answer number: ");
      Scanner in = new Scanner(System.in);
      while (!in.hasNextInt()) {
        System.out.println("Input should contains answer's number");
        in = new Scanner(System.in);
      }
      int answerNumber = in.nextInt();
      question.setRightAnswered(answerNumber == question.getRightAnswer());
    }
  }

  @Override
  public void printResult() {
    System.out.println("\nResults");
    for (Question question : survey.getQuestions()) {
      System.out.println("Question: " + question.getText() + ", answer is: " + question.isRightAnswered());
    }
  }
}
