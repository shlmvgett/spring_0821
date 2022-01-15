package com.ots.springbooks.actuators;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Component
public class IntegrationHealthIndicator implements HealthIndicator {

  private final String SERVICE_3D_PARTY_URL = "https://www.google.com";

  @Override
  public Health health() {
    ResponseEntity<String> response =
        new RestTemplate().getForEntity(SERVICE_3D_PARTY_URL, String.class);

    if (!response.getStatusCode().is2xxSuccessful()) {
      return Health.down()
          .status(Status.DOWN)
          .withDetail("message", "Something goes wrong.")
          .build();
    } else {
      return Health.up().withDetail("message", "Success.").build();
    }
  }
}
