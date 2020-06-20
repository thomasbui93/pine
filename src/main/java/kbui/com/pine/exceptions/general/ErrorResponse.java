package kbui.com.pine.exceptions.general;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
  static String DEFAULT_CODE = "PINE_GENERAL";

  private String errorCode;
  private String errorMessage;
  private HttpStatus statusCode;
}
