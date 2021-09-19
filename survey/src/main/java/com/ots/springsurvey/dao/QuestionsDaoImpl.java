package com.ots.springsurvey.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.ots.springsurvey.domain.Question;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class QuestionsDaoImpl implements QuestionsDao {

  private final String surveyPath;

  public QuestionsDaoImpl(@Value("${survey.path}") String surveyPath) {
    this.surveyPath = surveyPath;
  }

  @Override
  public List<Question> getQuestions() {
    List<Question> questions = new ArrayList<>();
    try (CSVReader csvReader =
             new CSVReader(Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(surveyPath).toURI())))) {
      String[] questionLine;
      while ((questionLine = csvReader.readNext()) != null) {
        List<String> line = List.of(questionLine);
        questions.add(new Question(
            Integer.parseInt(line.get(0)),
            line.get(1),
            Integer.parseInt(line.get(2)),
            line.subList(3, line.size())));
      }
    } catch (IOException | CsvException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
    return questions;
  }
}
