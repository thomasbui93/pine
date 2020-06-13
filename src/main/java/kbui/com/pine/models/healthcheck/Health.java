package kbui.com.pine.models.healthcheck;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Health {
  private String service;
  private Boolean status;
  private String errorMessage;
  private Boolean isFatal;
  private String resource;
}
