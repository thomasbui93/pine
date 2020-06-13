package kbui.com.pine.controllers;

import java.util.List;
import kbui.com.pine.models.healthcheck.ApplicationHealth;
import kbui.com.pine.models.healthcheck.Health;
import kbui.com.pine.services.healthcheck.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @Autowired HealthCheckService healthCheckService;

  @GetMapping(value = "/z/diagnostics")
  @ResponseBody
  public ApplicationHealth checkAction() {
    List<Health> services = this.healthCheckService.getHealthCheck();

    return new ApplicationHealth(services);
  }
}
