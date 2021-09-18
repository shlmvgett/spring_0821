package com.ots.springsurvey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

  private final MessageSource messageSource;
  private final Locale locale;
  private final InputStream inputStream;
  private final PrintStream printStream;

  @Autowired
  public IOServiceImpl(MessageSource messageSource, @Value("${survey.locale}") String locale) {
    this.messageSource = messageSource;
    this.locale = Locale.forLanguageTag(locale);
    this.inputStream = System.in;
    this.printStream = System.out;
  }

  @Override
  public Scanner in() {
    return new Scanner(inputStream);
  }

  @Override
  public Scanner in(String message) {
    out(message);
    return new Scanner(inputStream);
  }

  @Override
  public void out(String outString) {
    out(outString, new Object());
  }

  @Override
  public void out(String outString, Object... params) {
    printStream.println(messageSource.getMessage(outString, params, locale));
  }
}
