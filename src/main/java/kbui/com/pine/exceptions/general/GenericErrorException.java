package kbui.com.pine.exceptions.general;

public class GenericErrorException extends RuntimeException {
  static final long serialVersionUID = -7034897190745766939L;

  public GenericErrorException(String message) {
    super(message);
  }
}
