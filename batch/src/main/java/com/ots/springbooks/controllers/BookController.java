package com.ots.springbooks.controllers;

import static com.ots.springbooks.config.JobConfig.JOB_NAME;

import com.ots.springbooks.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class BookController {

  private BookService bookService;
  private JobOperator jobOperator;

  @ShellMethod(
      value = "Get all Books from Jpa",
      key = {"gabj"})
  public void getAllBooksJpa() {
    bookService.getAllBooksJpa();
  }

  @ShellMethod(
      value = "Get all Books from Mongo",
      key = {"gabm"})
  public void getAllBooksMongo() {
    bookService.getAllBooksMongo();
  }

  @ShellMethod(
      value = "Migrate from Jpa to Mongo",
      key = {"m"})
  public void migrate()
      throws JobInstanceAlreadyExistsException, NoSuchJobException, JobParametersInvalidException,
          NoSuchJobExecutionException {
    Long executionId = jobOperator.start(JOB_NAME, "");
    System.out.println("Migration status: " + jobOperator.getSummary(executionId));
  }
}
