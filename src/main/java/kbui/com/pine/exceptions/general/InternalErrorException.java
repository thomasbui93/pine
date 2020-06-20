package kbui.com.pine.exceptions.general;

public class InternalErrorException extends RuntimeException {
  static final long serialVersionUID = -7034897190745766939L;

  public InternalErrorException(String message) {
    super(message);
  }
}
