package com.ots.springbooks.service.impl;

import com.ots.springbooks.models.UserData;
import com.ots.springbooks.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class AuthServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String login) {
    UserData userData =
        userRepository
            .findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("Unknown user with login:" + login));
    return new User(
        userData.getLogin(),
        userData.getPassword(),
        AuthorityUtils.createAuthorityList(userData.getRole()));
  }
}
