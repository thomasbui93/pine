package kbui.com.pine.exceptions.general;

import java.sql.SQLException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
	protected ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    String errorMessage = "Bad request input.";    
    ErrorResponse errorResponse = new ErrorResponse(ErrorResponse.DEFAULT_CODE, errorMessage, status);
		return buildErrorResponse(errorResponse);
  }

  @ExceptionHandler(SQLException.class)
  public ResponseEntity<Object> handleSQLError(Exception ex, WebRequest request) {
    String errorMessage = "SQL error exception.";
    ErrorResponse errorResponse = new ErrorResponse(ErrorResponse.DEFAULT_CODE, errorMessage, HttpStatus.BAD_REQUEST);
    return buildErrorResponse(errorResponse);
  }
  
  private ResponseEntity<Object> buildErrorResponse(ErrorResponse error) {
    return new ResponseEntity<>(error, error.getStatusCode());
  }
}
