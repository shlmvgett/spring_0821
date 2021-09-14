package com.ots.springsurvey.config;

import com.ots.springsurvey.domain.Survey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public Survey survey() {
    return new Survey();
  }
}
