package com.ots.springbooks.service;

import com.ots.springbooks.service.interfaces.IOService;
import java.util.Scanner;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class IOServiceImpl implements IOService {

  @Override
  public void print(String message) {
    System.out.println(message);
  }

  @Override
  public String read() {
    return new Scanner(System.in).nextLine();
  }
}
