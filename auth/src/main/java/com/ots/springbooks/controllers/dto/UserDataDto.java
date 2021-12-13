package com.ots.springbooks.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Getter
@ToString
public class UserDataDto {

  private final String username;
  private final List<String> roles;

  public UserDataDto(Authentication authentication) {
    this.username = authentication.getName();
    this.roles =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
  }
}
