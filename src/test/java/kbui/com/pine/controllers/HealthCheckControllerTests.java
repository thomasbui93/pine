package kbui.com.pine.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import kbui.com.pine.models.healthcheck.ApplicationHealth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HealthCheckControllerTests {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void healthShouldBeAnArray() throws Exception {
    String url = "http://localhost:" + port + "/z/diagnostics";
    ApplicationHealth health = this.restTemplate.getForObject(url, ApplicationHealth.class);
    assertThat(health.getServices()).asList();
    assertThat(health.getServices().size()).isEqualTo(2);
  }
}
