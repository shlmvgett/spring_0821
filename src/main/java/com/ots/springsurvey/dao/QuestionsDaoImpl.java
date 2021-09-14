package com.ots.springsurvey.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.ots.springsurvey.domain.Question;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Getter
@Component
public class QuestionsDaoImpl implements QuestionsDao {

  @Value("${survey.path}")
  private String surveyPath;

  @Override
  public List<Question> getContent() {
    List<Question> questions = new ArrayList<>();
    try (Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("survey.csv").toURI()))) {
      String[] questionLine;
      CSVReader csvReader = new CSVReader(reader);
      while ((questionLine = csvReader.readNext()) != null) {
        List<String> line = List.of(questionLine);
        questions.add(new Question(line.get(0), Integer.parseInt(line.get(1)), line.subList(2, line.size())));
      }
      csvReader.close();
    } catch (IOException | CsvException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
    return questions;
  }
}
