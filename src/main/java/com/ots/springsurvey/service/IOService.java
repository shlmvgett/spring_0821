package com.ots.springsurvey.service;

import java.util.Scanner;

public interface IOService {

  Scanner in();

  Scanner in(String message);

  void out(String out);

  void out(String out, Object... params);
}
