package kbui.com.pine.exceptions.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
  static String DEFAULT_CODE = "PINE_GENERAL";

  private String errorCode;
  private String errorMessage;
}
