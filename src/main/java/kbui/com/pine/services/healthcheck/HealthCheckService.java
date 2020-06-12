package kbui.com.pine.services.healthcheck;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import kbui.com.pine.models.healthcheck.Health;

@Service
public class HealthCheckService {
  public Health getRedisDiagnostic() {
    return new Health("redis", true, "", true, "");
  }

  public Health getSqlDiagnostic() {
    return new Health("sql", true, "", true, "");
  }

  public List<Health> getHealthCheck() {
    Health redis = this.getRedisDiagnostic();
    Health sql = this.getSqlDiagnostic();
    List<Health> services = Arrays.asList(redis, sql);

    return services;
  }
}
