package com.ots.springbooks.controllers;

import com.ots.springbooks.controllers.dto.UserDataDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@AllArgsConstructor
public class UserRestController {

  @GetMapping("/api/user")
  public UserDataDto getUser(Authentication authentication) {
    return new UserDataDto(authentication);
  }
}
