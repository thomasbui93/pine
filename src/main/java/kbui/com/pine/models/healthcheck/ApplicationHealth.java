package kbui.com.pine.models.healthcheck;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationHealth {
  private List<Health> services;
}
