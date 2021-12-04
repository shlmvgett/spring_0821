package com.ots.springbooks.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
@AllArgsConstructor
public class BookController {

  @GetMapping("/")
  public String listPage() {
    return "list";
  }
}
